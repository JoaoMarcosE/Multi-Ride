<template>
  <div class="page-body">
    <div class="pick-starting-place">
      <PickLocation text="Starting Place" locationKey="starting" />
    </div>
    <div class="pick-destination-place">
      <PickLocation text="Destination" locationKey="destination"/>
    </div>
    <div class="map-frame">
      <Map />
    </div>
    <div class="result-frame">
      <TravelsResult />
    </div>
  </div>
</template>

<script>
import PickLocation from "./PickLocation.vue";
import Map from "./Map.vue";
import TravelsResult from "./TravelsResult.vue"
export default {
  name: "PageBody",
  components: { PickLocation, Map, TravelsResult },
  data() {
    return {};
  },
  beforeCreate() {}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.page-body {
  width: 100%;
  margin-top: 134px;
}

.result-frame {
  margin-bottom: 30px;
}

@media screen and (min-width: 576px) {
  .page-body {
    width: 100%;
    margin-top: 100px;
  }
}
</style>
