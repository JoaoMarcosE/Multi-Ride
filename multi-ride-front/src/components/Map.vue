<template>
  <div class="form-group map-group">
    <div id="map-visualizer"></div>
  </div>
</template>

<script>
import GoogleMapsLoader from "google-maps";
import { mapState } from "vuex";

export default {
  name: "Map",
  props: {},
  data() {
    return {
      mapObj: null,
      googleService: null,
      directionsService: null,
      directionsRenderer: null,
      startingLocation: null,
      destinationLocation: null
    };
  },
  computed: mapState(["starting", "destination"]),
  created() {
    this.$store.subscribe((mutation, state) => {
      if (mutation.type === "updateStarting") {
        this.startingLocation = state.starting;
      } else if (mutation.type === "updateDestination") {
        this.destinationLocation = state.destination;
      }
      this.refreshRoute();
    });
  },
  mounted() {
    GoogleMapsLoader.LIBRARIES = ["places"];
    GoogleMapsLoader.KEY = localStorage.getItem("keyGoogleAPI");
    GoogleMapsLoader.load(google => {
      this.googleService = google;
      this.initializeMap();
    });
  },
  methods: {
    createMap(currentLocation) {
      this.directionsService = new this.googleService.maps.DirectionsService();
      this.directionsRenderer = new this.googleService.maps.DirectionsRenderer();

      this.mapObj = new this.googleService.maps.Map(
        document.getElementById("map-visualizer"),
        { zoom: 17, center: currentLocation }
      );

      this.directionsRenderer.setMap(this.mapObj);
    },
    setMapLocation(location) {
      this.mapObj.setCenter(location);
    },
    initializeMap() {
      if (navigator.geolocation) {
        //initialize with current location
        navigator.geolocation.getCurrentPosition(position => {
          let currentLocation = new this.googleService.maps.LatLng(
            position.coords.latitude,
            position.coords.longitude
          );
          this.createMap(currentLocation);
        });
      } else {
        this.createMap(null);
      }
    },
    refreshRoute() {
      console.log("refresh na rota");
      debugger;
      if (this.startingLocation && this.destinationLocation) {
        let request = {
          origin: this.startingLocation,
          destination: this.destinationLocation,
          travelMode: "DRIVING"
        };

        this.directionsService.route(request, (result, status) => {
          debugger;

          if (status == "OK") {
            this.directionsRenderer.setDirections(result);
          }
        });
      } else {
        //sem as duas posições, então volta ao original
        if (navigator.geolocation) {
          //initialize with current location
          navigator.geolocation.getCurrentPosition(position => {
            let currentLocation = new this.googleService.maps.LatLng(
              position.coords.latitude,
              position.coords.longitude
            );
            this.setMapLocation(currentLocation);
          });
        } else {
          this.setMapLocation(null);
        }
        this.directionsRenderer.setDirections(null);
      }
    }
  }
};
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.map-group {
  width: 100%;
  margin: auto;
  padding: 0px;
  margin-top: 20px;
}

#map-visualizer {
  height: 200px; /* The height is 400 pixels */
  width: 100%; /* The width is the width of the web page */
}
@media print {
  #map-visualizer {
    height: 200px; /* The height is 400 pixels */
    width: 100%; /* The width is the width of the web page */
  }
}
</style>
