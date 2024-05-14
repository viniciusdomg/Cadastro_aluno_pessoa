<template>
    <div class="container-principal">
      <div class="buttonsContainer">
        <router-link to="/aluno"><button class="buttonOrbital">Tela Aluno</button></router-link>
        <router-link to="/pessoa"><button class="buttonOrbital">Tela Pessoa</button></router-link>
      </div>
    </div>
</template>

<script>
export default {
    name : 'HomeView'
}
</script>

<style scoped>
  .container-principal{
    height:88%;
    display:flex;
    align-items:center;
    justify-content: center;
    
  }

  .buttonsContainer{
    display:flex;
    justify-content: center;
    width:25%;
    justify-content: space-around;
  }

  .buttonOrbital{
    height: 50px;
    width: 100px;
    border-radius: 10px;
    border: none;
    color: white;
    background-color: #236bcb;
    cursor: pointer;
    box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.4); 
    transition: box-shadow 0.3s ease;
  }

  .buttonOrbital:hover {
      background-color: #6aa5ff;
      box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.4); 
  }

  .buttonOrbital:active {
      transform: translateY(3px); 
      box-shadow: none; 
  }
</style>