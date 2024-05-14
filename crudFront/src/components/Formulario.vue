<template>
    <div>
        <section class="container">
            <div class="container-form">
                <h3 v-if="pagina==='/pessoa/cadastrar'"><strong>Formulário para Cadastro de Nova Pessoa</strong></h3>
                <h3 v-else><strong>Formulário para Cadastro de Novo Aluno</strong></h3>
                
                <router-link v-if="pagina==='/pessoa/cadastrar'" to="/pessoa" class="link"> <svg-icon type="mdi" :path="returnIcon"></svg-icon> <p>Página Listar</p></router-link>
                <router-link v-else to="/aluno" class="link"> <svg-icon type="mdi" :path="returnIcon"></svg-icon> <p>Página Listar</p></router-link>
                
                <form v-show="pagina==='/pessoa/cadastrar'" @submit="enviarForm($event)" method="post">
                    <div class="box-input">
                        <label class="label-width" for="matricula">Nome:</label>
                        <input type="text" class="nome" id="nome" name="nome" v-model="inputNome" placeholder="Digite o Nome" required>                    
                    </div>
                    <div class="box-input">
                        <label class="label-width" for="cpf">CPF:</label>
                        <input type="text" class="identificacao" id="cpf" name="cpf" v-model="inputCpf" placeholder="Digite o CPF" required>
                    </div>
                    <div class="box-buttons">
                        <input type="submit" value="Confirmar" class="btn-submit">
                        <input type="button" class="btn-cancel" @click="cancelFormPessoa()" value="Cancelar">
                    </div>
                </form>

                <form v-show="pagina==='/aluno/cadastrar'" @submit="enviarForm($event)" method="post">
                    <div class="box-input">
                        <label class="label-width" for="matricula">Matrícula:</label>
                        <input type="text" class="identificacao" id="matricula" name="matricula" v-model="inputMatricula" placeholder="Digite a matricula" required>
                    </div>
                    <div class="box-input">
                        <label class="label-width" for="cpfPessoa">CPF da pessoa:</label>
                        <input type="text" class="nome" id="cpfPessoa" name="cpfPessoa" v-model="inputCpf" placeholder="Digite o CPF" required>
                    </div>
                    <div class="box-input">
                        <label class="label-width" for="anoIngresso">Ano de Ingresso:</label>
                        <input type="number" class="anoIngresso" id="anoIngresso" name="anoIngresso" v-model="inputAnoIngresso" placeholder="Digite o Ano de Ingresso" required>
                    </div>
                    <div class="box-buttons">
                        <input type="submit" value="Confirmar" class="btn-submit">
                        <input type="button" class="btn-cancel" @click="cancelFormAluno()" value="Cancelar">
                    </div>
                </form>
            </div>
        </section>
    </div>
</template>

<script>
    import axios from 'axios';
    import SvgIcon from '@jamescoyle/vue-icon';
    import { mdiKeyboardBackspace  } from '@mdi/js';
    export default {
        name : 'FormComponent',
        computed : {
            pagina() {
                return this.$route.path
            }
        },
        components: {
            SvgIcon
        },
        data(){
            return{
                inputNome : '',
                inputCpf : '',
                inputMatricula: '',
                inputAnoIngresso: '',
                returnIcon: mdiKeyboardBackspace
            }
        },
        methods: {
            cancelFormPessoa(){
                this.$router.push('/pessoa');
            },
            cancelFormAluno(){
                this.$router.push('/aluno');
            },
            enviarForm(e){
                e.preventDefault();
                if(this.$route.path == "/pessoa/cadastrar"){
                    const formData = new FormData(e.target);
                    axios.post('http://localhost:8080/Pessoa/Cadastrando', formData)
                        .then(response => { 
                            this.inputNome = '';
                            this.inputCpf = '';
                            alert(response.data);
                            setTimeout(() => {
                                this.$router.push('/pessoa')
                            },1000); 
                        })
                        .catch(error => { 
                            this.inputCpf = '';
                            alert(error.response.data); 
                        })
                }else {
                    const formData = new FormData(e.target);
                    axios.post('http://localhost:8080/Graduacao/Cadastrando', formData)
                        .then(response => { 
                            this.inputMatricula = '';
                            this.inputCpf = '';
                            this.inputAnoIngresso = '';
                            alert(response.data);
                            setTimeout (() => {
                                this.$router.push('/aluno')
                            })
                        })
                        .catch(error => {
                            this.inputMatricula = '';
                            this.inputCpf = '';
                            alert(error.response.data);
                        })
                }
            }
        }
    }
</script>

<style scoped>
    .container {
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .link{
        width: 120px;
        display: flex;
        align-items: center;
        color: black;
    }

    .link:hover{
        text-decoration: none;
        color: #236bcb;
    }

    svg{
        margin-right: 5px;
    }

    .container-form{
        width: 469px;
    }

    .container-form h3{
        font-size: 22px;
        margin-bottom: 16px;
    }

    form{
        margin-top: 16px;
        width: 80%;
    }

    .box-input{
        width: 100%;
        margin-bottom: 10px;
    }

    .label-width{
        display: inline-block;
        width: 115px;
        cursor: pointer;
    }

    .identificacao{
        width: 120px;
    }

    .nome{
        width: 60%;
    }

    .anoIngresso{
        width: 55px;
    }

    .box-buttons{
        width: 100%;
        display: flex;
        justify-content: space-evenly;
    }

    .btn-submit, .btn-cancel{
        cursor: pointer;
        color: white;
        border: none;
        border-radius: 5px;
        width: 90px;
        height: 25px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
    }

    .btn-submit{
        background-color: #65b307;
    }

    .btn-cancel{
        background-color: #ee4242;
    }

    @media (min-width: 768px) {
        .lista {
            width: 60%;
        }
        header h1{
            font-size: 40px;
        }
        .container-form h3{
            font-size: 24px;
        }
    }
</style>