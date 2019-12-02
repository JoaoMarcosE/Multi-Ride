using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace WebServiceHTTPS.Models
{
    public class HttpStatusCodeResult : ActionResult
    {
        private readonly int code;
        public HttpStatusCodeResult(int code)
        {
            this.code = code;
        }

        public override void ExecuteResult(System.Web.Mvc.ControllerContext context)
        {
            context.HttpContext.Response.StatusCode = code;
        }
    }
}