<template>
  <!---->
  <div>
    <b-button class="historyButton" @click="showHistory">
      <unicon name="history" fill="blue"></unicon>
    </b-button>

    <b-modal id="historyModal" title="History" hide-footer size="xl" scrollable centered>
      <div v-if="!isLoading && historyRecords.length > 0">
        <TravelInfo
          v-for="travel in historyRecords"
          v-bind:key="travel.id"
          v-bind:travel="travel"
          v-bind:showLink="false"
        ></TravelInfo>
      </div>
      <div v-if="!isLoading && historyRecords.length === 0">
        <span>Parece que você ainda não fez nenhuma viagem!</span>
      </div>
    </b-modal>
  </div>
</template>

<script>
import TravelInfo from "./TravelInfo.vue";

export default {
  name: "History",
  props: {},
  components: { TravelInfo },
  data() {
    return { modalIsOpen: false, isLoading: true, historyRecords: [] };
  },
  methods: {
    showHistory() {
      this.isLoading = true;
      this.$bvModal.show("historyModal");

      this.historyRecords = [];
      let localSto = localStorage.getItem("history");
      if (localSto) {
        let storageHistory = JSON.parse(localSto);
        if (storageHistory) {
          storageHistory.forEach((val, key) => {
            this.historyRecords.push(val);
          });
        }
      }

      this.isLoading = false;
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.center {
  margin: auto;
  width: 64px;
  height: 64px;
}
.historyButton {
  display: inline-flex;
  margin-right: 10px;
}
</style>
