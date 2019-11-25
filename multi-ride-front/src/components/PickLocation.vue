<template>
  <div class="form-group location-group">
    <label>{{text}}</label>
    <input type="text" class="form-control" placeholder="Informe um local" ref="picklocation" />
  </div>
</template>

<script>
import GoogleMapsLoader from "google-maps";
export default {
  name: "PickLocation",
  props: {
    text: String,
    locationKey: String
  },
  data() {
    return {
      autoCompleteObj: null,
      googleService: null
    };
  },
  mounted() {
    GoogleMapsLoader.LIBRARIES = ["places"];
    GoogleMapsLoader.KEY = localStorage.getItem("keyGoogleAPI");
    GoogleMapsLoader.load(google => {
      this.googleService = google;
      this.initializeGoogle();
    });
  },
  methods: {
    initializeGoogle() {
      // Create the autocomplete object, restricting the search predictions to
      // geographical location types.

      this.autoCompleteObj = new this.googleService.maps.places.Autocomplete(
        this.$refs.picklocation,
        { types: ["geocode"], fields: ['geometry.location', 'address_components'] }
      );
      // Avoid paying for data that you don't need by restricting the set of
      // place fields that are returned to just the address components.
      this.autoCompleteObj.setFields(["address_component"]);
      // When the user selects an address from the drop-down, populate the
      // address fields in the form.
      this.autoCompleteObj.addListener("place_changed", () => {
        var place = this.autoCompleteObj.getPlace();

        this.$store.dispatch(
          "fetch_" + this.locationKey,
          place.geometry ? 
          new this.googleService.maps.LatLng(
            place.geometry.location.lat(),
            place.geometry.location.lng()
          ) : null
        );
      });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.location-group {
  width: 90%;
  margin: auto;
  padding: 0px;
  margin-top: 20px;
}

#locationField,
#controls {
  position: relative;
  width: 480px;
}
#autocomplete {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 99%;
}
.label {
  text-align: right;
  font-weight: bold;
  width: 100px;
  color: #303030;
  font-family: "Roboto";
}
#address {
  border: 1px solid #000090;
  background-color: #f0f9ff;
  width: 480px;
  padding-right: 2px;
}
#address td {
  font-size: 10pt;
}
.field {
  width: 99%;
}
.slimField {
  width: 80px;
}
.wideField {
  width: 200px;
}
#locationField {
  height: 20px;
  margin-bottom: 2px;
}
</style>
