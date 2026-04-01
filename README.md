 🚗 Parking Dashboard (Android + Backend Integration)

Projeto de interface mobile para visualização e controle de estacionamento, integrado com uma API backend desenvolvida em Kotlin + Spring Boot.

 📱 Sobre o projeto

Este app simula um painel de estacionamento onde é possível:

- Visualizar vagas disponíveis e ocupadas
- Ver dados dos veículos (placa, horário de entrada, id da vaga)
- Acompanhar o faturamento em uma guarita financeira
- Validar dados reais vindos do backend

A aplicação consome uma API que gerencia:
- entrada e saída de veículos
- cálculo de valores
- persistência em banco de dados PostgreSQL

---

 🧠 Arquitetura

O projeto foi estruturado em camadas para manter organização e escalabilidade:

- `data` → comunicação com API (Retrofit)
- `domain` → modelos e regras
- `presentation` → UI (Jetpack Compose)
- `ui.theme` → tema e configurações visuais

---

 ⚙️ Tecnologias utilizadas

 Mobile
- Kotlin
- Jetpack Compose
- Retrofit
- StateFlow / ViewModel
- Material 3

 Backend (integrado)
- Kotlin
- Spring Boot
- PostgreSQL
- Docker
- Webhook

---

 🔄 Fluxo da aplicação

1. Veículo entra → backend registra entrada
2. Vaga é ocupada automaticamente
3. Ao sair:
   - calcula valor
   - salva no banco
   - atualiza financeiro
4. Interface consome API e atualiza os dados

---

 📊 Funcionalidades

- Painel de vagas (ocupadas/livres)
- Dados em tempo real da API
- Guarita financeira com total e histórico
- Integração completa com backend

---

 🚀 Próximos passos

- Atualização em tempo real (polling ou websocket)
- Melhorias visuais na UI
- Filtros por setor
- Dashboard mais detalhado

---

 💡 Aprendizados

Esse projeto me ajudou a evoluir principalmente em:

- Integração entre backend e mobile
- Consumo de API REST
- Organização em arquitetura em camadas
- Tratamento de estados (loading, erro, sucesso)
- Validação de dados reais com banco

---

 👨‍💻 Autor

Desenvolvido por Abel Pozza  
