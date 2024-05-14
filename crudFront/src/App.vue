<template>
  <div class="container-geral">
    <HeaderComponent/>
    <router-view/>
  </div>
</template>

<script>
  import HeaderComponent from './components/Header.vue'
  export default {
    name : 'App',
    components: { 
      HeaderComponent, 
    }
  }
</script>

<style scoped>
    .container-geral{
      height: 100%;
    }
</style>
