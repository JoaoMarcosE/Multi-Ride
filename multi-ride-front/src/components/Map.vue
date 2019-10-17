<template>
  <div class="form-group map-group">
    <div id="map"></div>
  </div>
</template>

<script>
import GoogleMapsLoader from "google-maps";
export default {
  name: "Map",
  props: {},
  data() {
    return {
      mapObj: null,
      googleService: null,
      directionsService: null,
      directionsRenderer: null
    };
  },
  mounted() {
    GoogleMapsLoader.LIBRARIES = ["places"];
    GoogleMapsLoader.KEY = "";
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
        document.getElementById("map"),
        { zoom: 7, center: currentLocation }
      );
      this.directionsRenderer.setMap(this.mapObj);
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
        //initialize without location
        this.createMap(null);
      }
    }
  }
};
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  margin-right: 400px;
  height: 100%;
}
@media print {
  #map {
    height: 500px;
    margin: 0;
  }
}
</style>
