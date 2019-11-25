<template>
  <div class="travels-result">
    <TravelInfo v-for="travel in travels" v-bind:key="travel.id" v-bind:travel="travel"></TravelInfo>
  </div>
</template>

<script>
import TravelInfo from "./TravelInfo.vue";
import { mapState } from "vuex";

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
      destinationLocation: null
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
      console.log("refresh nos valores");
      debugger;
      if (this.startingLocation && this.destinationLocation) {
        // axios.post("/user", {
        //   start: this.startingLocation,
        //   destination: this.destinationLocation
        // })
        // .then(function(response) {
        //   console.log(response);
        // })
        // .catch(function(error) {
        //   console.log(error);
        // });
        this.travels = [
          {
            Imagem: "uber.webp",
            MenorValor: "25,00",
            MaiorValor: "45,00",
            MenorTempo: 15,
            MaiorTempo: 25,
            id: 1,
            Url: "https://m.uber.com/ul/?action=setPickup&pickup[latitude]=37.775818&pickup[longitude]=-122.418028&pickup[nickname]=UberHQ&pickup[formatted_address]=1455%20Market%20St%2C%20San%20Francisco%2C%20CA%2094103&dropoff[latitude]=37.802374&dropoff[longitude]=-122.405818&dropoff[nickname]=Coit%20Tower&dropoff[formatted_address]=1%20Telegraph%20Hill%20Blvd%2C%20San%20Francisco%2C%20CA%2094133&product_id=a1111c8c-c720-46c3-8534-2fcdd730040d#"
          }
        ];
      } else {
        //sem as duas posições, então esconde o resultado
        this.hideResult();
      }
    },
    hideResult() {
      this.travels = [];
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
