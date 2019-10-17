<template>
  <div class="page-body">
    <div class="pick-starting-place">
      <PickLocation text="Starting Place" />
    </div>
    <div class="pick-destination-place">
      <PickLocation text="Destination" />
    </div>
  </div>
</template>

<script>
import PickLocation from "./PickLocation.vue";

export default {
  name: "PageBody",
  components: { PickLocation },
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

@media screen and (min-width: 576px) {
  .page-body {
    width: 100%;
    margin-top: 100px;
  }
}
</style>
