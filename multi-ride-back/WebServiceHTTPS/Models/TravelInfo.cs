using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebServiceTeste.Models
{
    public class TravelInfo
    {
        public double PickupLat { get; set; }
        public double PickupLng { get; set; }
        public double DropoffLat { get; set; }
        public double DropoffLng { get; set; }
    }
}