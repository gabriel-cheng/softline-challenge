# Soft-Line Code Challenge

> Repositório do desafio técnico para Desenvolvedor Pleno da Soft-Line Sistemas.

## 🌐 Aplicação em Produção

Acesse: [https://ghcarvalho.com.br](https://ghcarvalho.com.br)

## 🛢️ Modelo das tabelas do Banco de Dados
![Tela do sistema](./docs/images/database-model.png)

## 🛣️ Modelo das rotas
![Tela do sistema](./docs/images/routes.png)

## 🚀 Como rodar este projeto

Você pode rodar o projeto de duas formas: **localmente** (Java + Maven + SQL Server instalados na sua máquina) ou via **Docker Compose** (sobe todos os serviços automaticamente, incluindo o banco de dados).

---

### 🖥️ Opção 1 — Rodando localmente

#### 📋 Requisitos

- ☕ **Java 17+** — [Download](https://www.oracle.com/java/technologies/downloads/)
- 📦 **Maven** — [Download](https://maven.apache.org/download.cgi)
- 🗄️ **SQL Server** — [Download](https://www.microsoft.com/en-us/sql-server/sql-server-downloads)
- 🛠️ **SQL Server Management Studio (SSMS)** — [Download](https://learn.microsoft.com/en-us/ssms/install/install)
- 💻 **Git** — [Download](https://git-scm.com/downloads)

#### ⚙️ Configuração

1. Clonando o repositório:

```bash
git clone http://github.com/gabriel-cheng/softline-challenge
cd softline-challenge
```

2. Criando o banco
> Se ainda não criou, crie o Banco de Dados da aplicação no SQL Server.

3. Configurando o `.env`
> - Copie e cole o arquivo `.env.example` na raíz do projeto <br>
> - Nomeie o arquivo copiado para `.env` <br>
> - Preencha as variáveis abaixo com os dados da sua instância local do SQL Server:

```env
DATABASE_URI=jdbc:sqlserver://localhost:1433;databaseName=softlineChallenge;encrypt=true;trustServerCertificate=true;
DATABASE_USERNAME=sa
DATABASE_PASSWORD=SuaSenhaAqui
JWT_SECRET=SeuSegredoAqui
```

4. Instalando as dependências
> - Na raíz do projeto, rode o seguinte comando e aguarde a instalação das dependências:
```bash
mvn install
```

5. Rodando o projeto
```bash
mvn spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

---

### 🐳 Opção 2 — Rodando com Docker Compose

#### 📋 Requisitos

- 🐳 **Docker** e **Docker Compose** — [Download](https://www.docker.com/products/docker-desktop/)
- 💻 **Git** — [Download](https://git-scm.com/downloads)

#### ⚙️ Configuração

1. Clonando o repositório:

```bash
git clone http://github.com/gabriel-cheng/softline-challenge
cd softline-challenge
```

2. Suba todos os serviços (SQL Server, backend, frontend e proxy Nginx):

```bash
docker compose up --build
```

O Docker Compose já cria o container do SQL Server, aguarda ele ficar saudável (healthcheck) e só então inicia o backend, evitando erros de conexão prematura.

3. Acesse a aplicação em `http://localhost`.

> As variáveis de ambiente (`DATABASE_URI`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`, `JWT_SECRET`) já vêm configuradas no `docker-compose.yml` para o ambiente local. Ajuste os valores diretamente nesse arquivo se precisar.

---

### ☸️ Deploy em Produção (Kubernetes)

Em produção, a aplicação roda em um cluster **k3s**, com:

- **Traefik** como Ingress Controller, roteando `/api` para o backend e `/` para o frontend
- **cert-manager** gerenciando certificados TLS via Let's Encrypt
- Variáveis de ambiente injetadas via `Secret` (`softline-secrets`) e lidas em runtime pelo pod

Os manifests (`Deployment`, `Service`, `Ingress`, `Middleware`) estão disponíveis na pasta `k8s/` do repositório.

---

## 🔑 Endpoints

> As rotas marcadas como **requer autenticação** exigem o cookie `auth_token`, obtido via `POST /auth/login`. Rotas de `products` e `customers` retornam e afetam apenas os registros pertencentes ao usuário autenticado.

### 🔐 Auth

**POST** `/auth/login` — realiza o login e define o cookie de autenticação _(rota pública)_
```json
{
    "username": "gabriel@user",
    "password": "gabriel123"
}
```

**POST** `/auth/logout` — encerra a sessão removendo o cookie _(rota pública, sem corpo de requisição)_

---

### 👤 Users

**POST** `/users` — cadastra um novo usuário _(rota pública)_
```json
{
    "username": "gabriel@user",
    "password": "gabriel123"
}
```

**GET** `/users/me` — retorna os dados do usuário autenticado _(requer autenticação, sem corpo de requisição)_

**PATCH** `/users/me` — atualiza o usuário autenticado; campos opcionais _(requer autenticação)_
```json
{
    "username": "gabriel@user",
    "password": "gabriel123"
}
```

---

### 📦 Products

**GET** `/products` — lista os produtos do usuário autenticado _(requer autenticação, sem corpo de requisição)_

**POST** `/products` — cadastra um novo produto vinculado ao usuário autenticado _(requer autenticação)_
```json
{
    "code": 10,
    "description": "Lorem ipsum dolor sit amet.",
    "bar_code": "78949007000155",
    "selling_price": 50.00,
    "gross_weight": 13.0,
    "net_weight": 12.05
}
```

**PATCH** `/products/{code}` — atualiza um produto do usuário autenticado; campos opcionais _(requer autenticação)_
```json
{
    "description": "Lorem ipsum dolor sit amet.",
    "bar_code": "78949007000155",
    "selling_price": 50.00,
    "gross_weight": 13.0,
    "net_weight": 12.05
}
```

**DELETE** `/products/{code}` — remove um produto do usuário autenticado _(requer autenticação, sem corpo de requisição)_

---

### 🧾 Customers

**GET** `/customers` — lista os clientes do usuário autenticado _(requer autenticação, sem corpo de requisição)_

**POST** `/customers` — cadastra um novo cliente vinculado ao usuário autenticado _(requer autenticação)_
```json
{
    "code": 10,
    "name": "John Doe",
    "nickname": "John Doe's corp.",
    "document": "91711805000107",
    "address": "7404 Hayes Burgs, Apt. 836, 82527-9356, West Jersey, Utah, Estados Unidos"
}
```

**PATCH** `/customers/{code}` — atualiza um cliente do usuário autenticado; campos opcionais _(requer autenticação)_
```json
{
    "name": "John Doe",
    "nickname": "John Doe's corp.",
    "document": "91711805000107",
    "address": "7404 Hayes Burgs, Apt. 836, 82527-9356, West Jersey, Utah, Estados Unidos"
}
```

**DELETE** `/customers/{code}` — remove um cliente do usuário autenticado _(requer autenticação, sem corpo de requisição)_