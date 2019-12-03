<template>
  <div>
    <div class="travels-result">
      <div v-if="isLoading && !isError">
        <b-spinner type="grow" label="Loading..."></b-spinner>
      </div>
      <div v-if="!isLoading && !isError">
        <TravelInfo
          v-for="travel in travels"
          v-bind:key="travel.id"
          v-bind:travel="travel"
          v-bind:showLink="true"
        ></TravelInfo>
      </div>
      <div v-if="isError">
        <h2>Ocorreu um erro ao carregar as informações, tente novamente mais tarde.</h2>
      </div>
    </div>
  </div>
</template>

<script>
import TravelInfo from "./TravelInfo.vue";
import { mapState } from "vuex";
import random from "random";
import $ from "jquery";
import axios from "axios";

export default {
  name: "TravelsResult",
  components: { TravelInfo },
  data() {
    return {
      travels: [
        //   {
        //   Imagem: "uber.webp",
        //   MenorValor: "25,00",
        //   MaiorValor: "45,00",
        //   MenorTempo: 15,
        //   MaiorTempo: 25,
        //   id: 1,
        //   Tipo: "Uber X",
        //   Url: url
        // }
      ],
      startingLocation: null,
      destinationLocation: null,
      isLoading: false,
      isError: false
    };
  },
  beforeCreate() {},
  computed: mapState(["starting", "destination"]),
  created() {
    this.$store.subscribe((mutation, state) => {
      if (mutation.type === "updateStarting") {
        this.startingLocation = state.starting;
      } else if (mutation.type === "updateDestination") {
        this.destinationLocation = state.destination;
      }
      this.refreshValues();
    });
  },
  methods: {
    refreshValues() {
      if (this.startingLocation && this.destinationLocation) {
        this.isLoading = true;
        this.isError = false;
        let jobject = {
          PickupLat: this.startingLocation.lat(),
          PickupLng: this.startingLocation.lng(),
          DropoffLat: this.destinationLocation.lat(),
          DropoffLng: this.destinationLocation.lng()
        };
        console.log(jobject);

        //Search for the itens
        $.ajax({
          url: "https://192.168.43.47:44370/EstimateTravel/Estimate",
          contentType: "application/json; charset=UTF-8",
          data: JSON.stringify(jobject),
          dataType: "json",
          timeout: 300000,
          type: "POST",
          success: result => {
            console.log(result);
            this.travels = [];

            for (var index in result) {
              let element = result[index];

              let isUber = element.Imagem == "uber.webp";
              this.travels.push({
                Imagem: element.Imagem,
                MenorValor: this.formatMoneyValue(element.MenorValor),
                MaiorValor: this.formatMoneyValue(element.MaiorValor),
                MenorTempo: element.MenorTempo,
                MaiorTempo: element.MaiorTempo,
                id: random.int(1, 99999999),
                Tipo: element.Tipo,
                Url: isUber
                  ? `https://m.uber.com/ul/?action=setPickup&pickup[latitude]=${this.startingLocation.lat()}&pickup[longitude]=${this.startingLocation.lng()}&dropoff[latitude]=${this.destinationLocation.lat()}&dropoff[longitude]=${this.destinationLocation.lng()}`
                  : ""
              });
            }
            this.isLoading = false;
          },
          error: (xhr, status, error) => {
            console.log(xhr);
            console.log(status);
            console.log(error);
            this.isLoading = false;
            this.isError = true;
          }
        });
      } else {
        //sem as duas posições, então esconde o resultado
        this.hideResult();
      }
    },
    hideResult() {
      this.travels = [];
    },
    formatMoneyValue(val) {
      return ("" + val.toFixed(2)).replace(".", ",");
    }
  }
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
