<template>
  <div class="form-group location-group">
    <label>{{text}}</label>
    <input type="text" class="form-control" placeholder="Informe um local" id="pick-location" />
  </div>
</template>

<script>
export default {
  name: "PickLocation",
  props: {
    text: String
  },
  mounted: function () {
    var input = document.getElementById('pick-location');
    var searchBox = new google.maps.places.SearchBox(input);
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
</style>
