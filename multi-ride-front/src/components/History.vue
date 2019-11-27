<template>
  <!---->
  <div>
    <b-button class="historyButton" @click="showHistory" ><unicon name="history" fill="blue"></unicon></b-button>
     
    <b-modal
      id="historyModal"
      title="History"
      hide-footer
      size="xl"
      v-b-modal.modal-scrollable
      v-b-modal.modal-center
    >
      <div v-if="!isLoading && historyRecords.length > 0">
        <TravelInfo v-for="travel in historyRecords" v-bind:key="travel.id" v-bind:travel="travel" v-bind:showLink="false"></TravelInfo>
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
  components: {TravelInfo},
  data() {
    return { modalIsOpen: false, isLoading: true, historyRecords: [] };
  },
  methods: {
    showHistory() {
      debugger;
      this.isLoading = true;
      this.$bvModal.show("historyModal");

      this.historyRecords = [];
      let storageHistory = JSON.parse(localStorage.getItem("history"));
      if (storageHistory) this.historyRecords.push(storageHistory[0]);

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
