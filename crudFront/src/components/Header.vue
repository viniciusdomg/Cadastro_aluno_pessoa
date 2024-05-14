<template>
    <div>
        <header>
            <h1> Universidade Equipe Orbitais</h1>
        </header>
    </div>
</template>

<script>
    export default {
      name : 'HeaderComponent'  
    }
</script>

<style scoped>
    header{
        background-color: #236bcb;
        height: 6em;    
        display: flex;
        align-items: center;
        justify-content: center;
        margin-bottom: 10px;
    }
    header h1{
        margin: 0;
        font-size: 30px;
        color: white;
        text-shadow: 4px 4px 4px rgba(0, 0, 0, 0.5);
    }
    
    @media (min-width: 768px) {
        header h1{
            font-size: 40px;
        }
    }
</style>