<template>
  <div class="travel-info">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-1">
          <div class="col">
            <img class="app-icon" :src="require(`@/logos/${travel.Imagem}`)" />
          </div>
        </div>
        <div class="col">
          <div class="row align-items-center row-space">
            <div class="col">
              <span>{{travel.MenorValor}} - {{travel.MaiorValor}} reais</span>
            </div>
          </div>
          <div class="row align-items-center">
            <div class="col">
              <span>{{travel.MenorTempo}} - {{travel.MaiorTempo}} minutos</span>
            </div>
          </div>
        </div>
        <div class="col-1">
          <a :href="travel.Url" class="btn btn-primary" role="button">Abrir</a>
        </div>
        <div class="col-1"></div>
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
  props: ["travel"]
  // beforeCreate() {},
  // computed: {
  //    imageUrl: function () {
  //     return 'url(\'' + this.values.travel.Image, + '\')'
  //   }
  // }
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

.row-space {
  margin-bottom: 15px;
}

@media screen and (min-width: 576px) {
  .app-icon {
    height: 128px;
    width: 128px;
  }
}
</style>
