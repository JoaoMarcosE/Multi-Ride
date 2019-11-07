<template>
  <div class="travels-result">
      <TravelInfo></TravelInfo>
  </div>
</template>

<script>

import TravelInfo from "./TravelInfo.vue"

export default {
  name: "TravelsResult",
  components: { TravelInfo },
  data() {
    return {};
  },
  beforeCreate() {}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* .page-body {
  width: 100%;
  margin-top: 134px;
}

@media screen and (min-width: 576px) {
  .page-body {
    width: 100%;
    margin-top: 100px;
  }
} */
.travels-result {
    margin-top: 20px;
}
</style>
