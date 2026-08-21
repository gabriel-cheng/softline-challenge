# Soft-Line Code Challenge

> Repositório do desafio técnico para Desenvolvedor Pleno da Soft-Line Sistemas.

## 🛢️ Database Tables Model
![Tela do sistema](./docs/images/database-model.png)

## 🛣️ Route Models
![Tela do sistema](./docs/images/routes.png)

## 🚀 How to Run This Project

### 📋 Requirements

Antes de rodar o projeto, certifique-se de instalar:

- ☕ **Java 17+** — [Download](https://www.oracle.com/java/technologies/downloads/)
- 📦 **Maven** — [Download](https://maven.apache.org/download.cgi)
- 🗄️ **SQL Server** — [Download](https://www.microsoft.com/en-us/sql-server/sql-server-downloads)
- 🛠️ **SQL Server Management Studio (SSMS)** — [Download](https://learn.microsoft.com/en-us/ssms/install/install)
- 💻 **Git** — [Download](https://git-scm.com/downloads)

### ⚙️ Configuration

1. Clonando o repositório:

```bash
git clone http://github.com/gabriel-cheng/softline-challenge
cd softline-challenge
```

2. Criando o banco
> Se ainda não criou, crie o Banco de Dados da aplicação no SQL Server.

3. Configurando o .env
> - Copie e cole o arquivo ```.env.example``` na raíz do projeto <br>
> - Nomeie o arquivo copiado para ```.env``` <br>
> - Informe a ```porta de conexão```, o ```nome```, o ```user``` e o ```password``` do banco da aplicação.

4. Configurando o settings.xml
> - Copie e cole o arquivo ```settings.xml.example``` na raíz do projeto <br>
> - Nomeie o arquivo copiado para ```settings.xml``` <br>
> - Informe o ```DATABASE_PORT```, o ```DATABASE_PASSWORD```, e o ```DATABASE_USER``` do banco da aplicação.

5. Rodando o projeto
```bash
mvn spring-boot:run
```

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
    "gross_weight": 13.0,
    "net_weight": 12.05
}
```

**PATCH** `/products/{code}` — atualiza um produto do usuário autenticado; campos opcionais _(requer autenticação)_
```json
{
    "description": "Lorem ipsum dolor sit amet.",
    "bar_code": "78949007000155",
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
