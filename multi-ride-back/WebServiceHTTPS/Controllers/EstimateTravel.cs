

using System;
using System.Collections.Generic;
using System.IO;
using System.Threading;
using System.Web;
using System.Web.Mvc;
using WebServiceHTTPS;
using WebServiceTeste.Models;

namespace WebServiceTeste.Controllers
{
    public class EstimateTravelController : Controller
    {
        public ActionResult Index()
        {
            ViewBag.Title = "Home Page";

            return View("Home");
        }


        [HttpPost]
        public JsonResult Estimate(TravelInfo travelInfo)
        {
            Random rnd = new Random(DateTime.Now.TimeOfDay.Milliseconds);
            var travelsList = new List<TravelResult>();

            string configPath = @"C:\Multiride\config.json";
            if (System.IO.File.Exists(configPath))
                System.IO.File.Delete(configPath);

            Newtonsoft.Json.Linq.JObject config = new Newtonsoft.Json.Linq.JObject();
            config["PickupLat"] = travelInfo.PickupLat;
            config["PickupLng"] = travelInfo.PickupLng;
            config["DropoffLat"] = travelInfo.DropoffLat;
            config["DropoffLng"] = travelInfo.DropoffLng;

            System.IO.File.WriteAllText(configPath, Newtonsoft.Json.JsonConvert.SerializeObject(config));

            string resultPath = @"C:\Multiride\result.json";
            string appPath = @"C:\Multiride\EstimateRequestApp.jar";
            if (System.IO.File.Exists(resultPath))
                System.IO.File.Delete(resultPath);

            var myProcess = new System.Diagnostics.Process();
            myProcess.StartInfo.UseShellExecute = false;
            myProcess.StartInfo.FileName = "java";
            myProcess.StartInfo.Arguments = @"-jar " + appPath;
            myProcess.Start();

            while (!System.IO.File.Exists(resultPath))
                Thread.Sleep(250);

            Newtonsoft.Json.Linq.JArray results = Newtonsoft.Json.JsonConvert.DeserializeObject<Newtonsoft.Json.Linq.JArray>(System.IO.File.ReadAllText(resultPath));

            foreach (var item in results)
            {
                travelsList.Add(new TravelResult()
                {
                    Imagem = Convert.ToString(item["Imagem"]),
                    MenorValor = Convert.ToDouble(item["MenorValor"]),
                    MaiorValor = Convert.ToDouble(item["MaiorValor"]),
                    MenorTempo = 0,
                    MaiorTempo = 0,
                    Tipo = Convert.ToString(item["Tipo"])
                });
            }

            Response.Headers.Add("Access-Control-Allow-Origin", "*");
            Response.Headers.Add("Access-Control-Allow-Headers", "*");
            Response.Headers.Add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            Response.Headers.Add("Access-Control-Allow-Credentials", "true");

            return Json(travelsList, JsonRequestBehavior.AllowGet);
        }

        [HttpOptions]
        public ActionResult Estimate()
        {
            Response.Headers.Add("Access-Control-Allow-Origin", "*");
            Response.Headers.Add("Access-Control-Allow-Headers", "*");
            Response.Headers.Add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // new[] { "GET, POST, PUT, DELETE, OPTIONS" }
            Response.Headers.Add("Access-Control-Allow-Credentials", "true");
            return new HttpStatusCodeResult(System.Net.HttpStatusCode.OK);
        }

    }
}