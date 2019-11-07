<template>
  <div class="travel-info">
      <div class="container">
          <div class="row">
              <div class="col-2"></div>
              <div class="col-4">
                  <img class="app-icon" src="./../../public/uber.webp">
              </div>
              <div class="col-2">
                  <span>25,00 - 30,00 reais</span>
              </div>
              <div class="col-2">
                  <span>23 - 42 minutos</span>
              </div>
              <div class="col-2"></div>
          </div>
      </div>
  </div>
</template>

<script>
export default {
  name: "TravelInfo",
  components: {},
  data() {
    return {};
  },
  beforeCreate() {}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.travel-info {
  width: 100%;
  margin-top: 10px;
}

.app-icon {
    height: 64px;
    width: 64px;
}

@media screen and (min-width: 576px) {
  .app-icon {
    height: 128px;
    width: 128px;
  }
}
</style>
