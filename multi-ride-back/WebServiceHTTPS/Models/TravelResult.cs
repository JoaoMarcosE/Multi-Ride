using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebServiceTeste.Models
{
    public class TravelResult
    {
        public string Imagem { get; set; }
        public double MenorValor { get; set; }
        public double MaiorValor { get; set; }
        public int MenorTempo { get; set; }
        public int MaiorTempo { get; set; }
        public string Tipo { get; set; }
    }
}