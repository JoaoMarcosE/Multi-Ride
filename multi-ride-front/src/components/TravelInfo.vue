<template>
  <div class="travel-info">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-3">
            <img class="app-icon" :src="require(`@/logos/${travel.Imagem}`)" />
        </div>
        <div class="col">
          <div class="row align-items-center row-space">
            <div class="col">
              <span>R$ {{travel.MenorValor}} - R$ {{travel.MaiorValor}}</span>
            </div>
          </div>
          <div class="row align-items-center">
            <div class="col">
              <span>{{travel.MenorTempo}} - {{travel.MaiorTempo}} minutos</span>
            </div>
          </div>
        </div>
        <div v-if="showLink" class="col-1">
          <a :href="travel.Url" class="btn btn-primary" role="button" @click="addToHistory">Abrir</a>
        </div>
        <div v-if="showLink" class="col-2"></div>
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
  props: ["travel", "showLink"],
  methods: {
    addToHistory() {
      let historyRecords = [];
      let localSto = localStorage.getItem("history");

      if (localSto) {
        let storageHistory = JSON.parse(localSto);
        if (storageHistory) {
          storageHistory.forEach((val, key) => {
            historyRecords.push(val);
          });
        }
      }

      historyRecords.push({
        Imagem: this.travel.Imagem,
        MenorValor: this.travel.MenorValor,
        MaiorValor: this.travel.MaiorValor,
        MenorTempo: this.travel.MenorTempo,
        MaiorTempo: this.travel.MaiorTempo,
        id: this.travel.id
      });
      localStorage.setItem("history", JSON.stringify(historyRecords));
    }
  }
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
