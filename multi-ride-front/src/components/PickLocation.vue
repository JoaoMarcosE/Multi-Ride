<template>
  <div class="form-group location-group">
    <label>{{text}}</label>
    <input
      type="text"
      class="form-control"
      placeholder="Informe um local"
      ref="picklocation"
      @focus="geolocate"
    />
  </div>
</template>

<script>
import GoogleMapsLoader from "google-maps";
export default {
  name: "PickLocation",
  props: {
    text: String
  },
  data() {
    return {
      autoCompleteObj: null,
      googleService: null
    };
  },
  mounted() {
    GoogleMapsLoader.LIBRARIES = ["places"];
    GoogleMapsLoader.KEY = "";
    GoogleMapsLoader.load(google => {
      this.googleService = google;
      this.initializeGoogle();
    });
  },
  methods: {
    geolocate() {},
    initializeGoogle() {
      // Create the autocomplete object, restricting the search predictions to
      // geographical location types.

      this.autoCompleteObj = new this.googleService.maps.places.Autocomplete(
        this.$refs.picklocation,
        { types: ["geocode"] }
      );
      // Avoid paying for data that you don't need by restricting the set of
      // place fields that are returned to just the address components.
      this.autoCompleteObj.setFields(["address_component"]);
      // When the user selects an address from the drop-down, populate the
      // address fields in the form.
      this.autoCompleteObj.addListener("place_changed", () => {
        var place = this.autoCompleteObj.getPlace();
        // Get each component of the address from the place details,
        // and then fill-in the corresponding field on the form.
        for (var i = 0; i < place.address_components.length; i++) {
          var addressType = place.address_components[i].types[0];
          var val = place.address_components[i];
          console.log(val);
        }
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
