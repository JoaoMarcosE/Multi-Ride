<template>
  <div>
    <span class="glyphicon glyphicon-time" aria-hidden="true" @click="showModal = true"></span>
    <HistoryModal v-if="showModal" @close="showModal = false"></HistoryModal>
  </div>
</template>

<script>
import HistoryModal from "./HistoryModal.vue";

export default {
  name: "History",
  props: {},
  components: { HistoryModal },
  data() {
    return { showModal: false };
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
