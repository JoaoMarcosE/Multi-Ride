<template>
  <div class="page-body">
    <div class="pick-starting-place">
      <PickLocation
        text="Starting Place"
        :googleAPI="googleAPI"
        :googleAPIPromise="googleAPIPromise"
      />
    </div>
    <div class="pick-destination-place">
      <PickLocation text="Destination" :googleAPI="googleAPI" :googleAPIPromise="googleAPIPromise" />
    </div>
  </div>
</template>

<script>
import GoogleMapsApiLoader from "google-maps-api-loader";
import PickLocation from "./PickLocation.vue";

export default {
  name: "PageBody",
  props: { googleAPI: Object },
  components: { PickLocation },
  data() {
    return {
      googleAPIPromise: null
    };
  },
  beforeCreate() {
    console.log("Inicio beforeCreate");
    debugger;
    this.googleAPIPromise = GoogleMapsApiLoader({
      libraries: ["places"],
      apiKey: ""
    }).then(googleMapApi => {
      debugger
      this.googleAPI = googleMapApi;
    });

    console.log("Fim beforeCreate");
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.page-body {
  width: 100%;
  margin-top: 134px;
}

@media screen and (min-width: 576px) {
  .page-body {
    width: 100%;
    margin-top: 100px;
  }
}
</style>
