<template>
  <div>
    <div class="travels-result">
      <div v-if="isLoading">
        <b-spinner type="grow" label="Loading..."></b-spinner>
      </div>
      <div v-if="!isLoading">
        <TravelInfo
          v-for="travel in travels"
          v-bind:key="travel.id"
          v-bind:travel="travel"
          v-bind:showLink="true"
        ></TravelInfo>
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
        //   id: 1
        // }
      ],
      startingLocation: null,
      destinationLocation: null,
      isLoading: false
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
        let jobject = {
          pickupLat: this.startingLocation.lat(),
          pickupLng: this.startingLocation.lng(),
          dropoffLat: this.destinationLocation.lat(),
          dropoffLng: this.destinationLocation.lng()
        };
        console.log(jobject);
        // $.post("http://10.13.37.124:8080/WSMultiride/app", jobject)
        //   .done(function(data) {
        //     debugger;
        //     alert("Data Loaded: " + data);
        //   })
        //   .fail(function(error) {
        //     debugger;
        //   });
        // axios
        //   .post("http://10.13.37.124:8080/WSMultiride/app", jobject)
        //   .then(function(response) {
        //     debugger;
        //     console.log(response);
        //   })
        //   .catch(function(error) {
        //     debugger;
        //     console.log(error);
        //   });

        let menorValor = random.float(10, 50);
        let menorTempo = random.int(5, 20);
        let isUber = true;
        this.travels = [
          {
            Imagem: "uber.webp",
            MenorValor: this.formatMoneyValue(menorValor),
            MaiorValor: this.formatMoneyValue(
              random.float(menorValor + 1, menorValor + 12)
            ),
            MenorTempo: menorTempo,
            MaiorTempo: random.int(menorTempo + 3, menorTempo + 17),
            id: random.int(1, 99999999),
            Tipo: "Car Pool",
            Url: isUber
              ? `https://m.uber.com/ul/?action=setPickup&pickup[latitude]=${this.startingLocation.lat()}&pickup[longitude]=${this.startingLocation.lng()}&dropoff[latitude]=${this.destinationLocation.lat()}&dropoff[longitude]=${this.destinationLocation.lng()}`
              : ""
          }
        ];
        this.isLoading = false;
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
