<template>
  <div class="header">
    <div class="container">
      <div class="row center">
        <div class="col-sm-3 logo-img center">
          <img alt="Vue logo" src="../assets/logo.png" />
        </div>
        <div class="col-sm-9 logo-text center">
          <h1 class="logo-text">Multi-Ride</h1>
        </div>
      </div>
    </div>
     <History></History>
  </div>
</template>

<script>
import History from "./History.vue"

export default {
  name: "PageHeader",
  components: { History },
  props: {}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.header {
  padding: 0px;
  margin: 0px;
  background: #555;
  color: #f1f1f1;
  position: fixed;
  top: 0;
  width: 100%;
  display: flex !important;
}

.center {
  margin: auto;
  padding: 0px;
  width: 60%;
}

.logo-text h1 {
font-size: 22px;

}
.logo-img img {
  height: 64px;
  width: 64px;
}
</style>
