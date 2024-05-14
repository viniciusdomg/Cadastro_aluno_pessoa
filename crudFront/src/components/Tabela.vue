<template>
    <div>
        <router-link to="/" class="home link"> <svg-icon type="mdi" :path="homeIcon"></svg-icon> <p> Home </p></router-link>	
    
        <section v-show="rota==='/pessoa'" class="container">
            <div class="lista">
                <h3><strong>Lista de Pessoas:</strong></h3>
                <router-link to="/pessoa/cadastrar" class="link">
                    <svg-icon type="mdi" :path="plusIcon"></svg-icon> <p> Cadastrar Nova Pessoa </p>
                </router-link>
                <table>
                    <tr>
                        <td>Nome</td>
                        <td>CPF</td>
                        <td>Ações</td>
                    </tr>
                    <tr v-for="(pessoa, index) in listaData" :key="index">
                        <td>{{pessoa.nome}}</td>
                        <td >{{pessoa.cpf}}</td>
                        <td>
                            <router-link :to="`/pessoa/atualizar/${pessoa.id}`" class="btn-edit">
                                  <svg-icon type="mdi" :path="pencilIcon"></svg-icon>
                            </router-link>
                            <button class="btn-remover" @click="deleteAction(pessoa.id)">
                                <svg-icon type="mdi" :path="trashIcon"></svg-icon>
                            </button>
                        </td>
                    </tr>
                </table>
            </div>
        </section>
        <section v-show="rota==='/aluno'" class="container">
            <div class="lista">
                <h3><strong>Lista de Alunos:</strong></h3>
                <router-link to="/aluno/cadastrar" class="link">
                    <svg-icon type="mdi" :path="plusIcon"></svg-icon> <p> Cadastrar Novo Aluno </p>
                </router-link>
                <table>
                    <tr>
                        <td>Matricula</td>
                        <td>Ano de ingresso</td>
                        <td>CPF</td>
                        <td>Ações</td>
                    </tr>
                    <tr v-for="(aluno, index) in listaData" :key="index">
                        <td>{{aluno.matricula}}</td>
                        <td>{{aluno.anoEntrada}}</td>
                        <td>{{aluno.cpfPessoa}}</td>
                        <td>
                            <router-link :to="`aluno/atualizar/${aluno.id}`" class="btn-edit">
                                  <svg-icon type="mdi" :path="pencilIcon"></svg-icon>
                            </router-link>
                            <button class="btn-remover" @click="deleteAction(aluno.id)">
                                <svg-icon type="mdi" :path="trashIcon"></svg-icon>
                            </button>
                        </td>
                    </tr>
                </table>
            </div>
        </section>
    </div>
</template>

<script>
    import axios from 'axios';
    import SvgIcon from '@jamescoyle/vue-icon';
    import { mdiHome, mdiPlus, mdiPencil, mdiDelete } from '@mdi/js';
    export default{
        name : 'TabelaComponent',
        data() {
            return {
                listaData: [],
                homeIcon: mdiHome,
                plusIcon: mdiPlus,
                pencilIcon: mdiPencil,
                trashIcon: mdiDelete    
            }
        },
        components: {
            SvgIcon
        },
        computed : {
            rota (){
                return this.$route.path;
            }
        },
        mounted(){
            this.carregarDados();
        },
        methods: {
            carregarDados(){
                if(this.$route.path === '/pessoa'){
                    axios.get('http://localhost:8080/Pessoa/Listar')
                        .then(response => {
                            this.listaData = response.data;
                        })
                        .catch(error => {
                            console.log("Erro ao carregar os dados: ", error);
                        });
                }else{
                    axios.get('http://localhost:8080/Graduacao/Listar')
                        .then(response => {
                            this.listaData = response.data;
                        })
                        .catch(error =>{
                            console.log("Erro ao carregar os dados: ", error);
                        }
                    );    
                }       
            },
            deleteAction(id){
                if(this.$route.path === '/pessoa'){
                    axios.delete(`http://localhost:8080/Pessoa/Excluir/${id}`)
                        .then(response => {
                            alert(response.data);
                            window.location.reload();
                        })
                        .catch(error => {
                            alert(error.response.data);
                        })
                }else{
                    axios.delete(`http://localhost:8080/Graduacao/Excluir/${id}`)
                        .then(response => {
                            alert(response.data);
                            window.location.reload();
                        })
                        .catch(error => {
                            console.log(error);
                            alert(error.response.data);
                        })
                }
            }
        }
    }
</script>

<style scoped>
    .home{
        margin-left: 15px;
    }

    svg {
        width: 24px;
        height: 24px;
        margin-right: 3px;
    }
    
    .container {
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .lista {
        width: 100%; 
    }

    table {
        width: 100%;
        margin-top: 10px;
    }

    table tr td{
        width: 25%;
    }

    .link{
        width: 200px;
        color: black;
        display: flex;
        align-items: center ;
    }

    .link:hover{
        text-decoration: none;
        color: #236bcb;
    }

    .btn-edit:hover{
        color: #7bfd7b;
    }

    .btn-remover{
        border: none;
        padding: 0;
        background-color: transparent;
        cursor: pointer;
    }

    .btn-remover:hover{
        color: #fd7b7b;
    }

    @media (min-width: 768px) {
        .lista {
            width: 60%;
        }
        .home{
            margin-left: 5px;
        }
    }
</style>
