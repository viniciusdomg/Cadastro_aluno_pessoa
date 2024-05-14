
import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/Home.vue';
import AlunoView from '../views/Aluno.vue';
import PessoaView from '../views/Pessoa.vue';
import CadastrarView from '../views/Cadastrar.vue';
import AtualizarView from '../views/Atualiza.vue'

const routes = [
  { path: '/', component: HomeView },
  { path: '/aluno', component: AlunoView },
  { path: '/pessoa', component: PessoaView },
  { path: '/pessoa/cadastrar', component: CadastrarView},
  { path: '/aluno/cadastrar', component: CadastrarView},
  { path: '/pessoa/atualizar/:id', component: AtualizarView},
  { path: '/aluno/atualizar/:id', component: AtualizarView}
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
