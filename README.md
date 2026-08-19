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
> Se ainda não criou, crie o Banco de Dados da aplicação no MySQL Server.

3. Configurando o projeto
> - Copie e cole o arquivo ```.env.example``` na raíz do projeto <br>
> - Nomeie o arquivo copiado para ```.env``` <br>
> - Informe a ```porta de conexão```, o ```nome```, o ```user``` e o ```password``` do banco da aplicação.

4. Rodando o projeto
```bash
mvn spring-boot:run
```

## 🔑 Endpoints

• To access ```products```, ex:
```
• Endpoint: http://localhost:8080/products

• Methods: [GET, POST, PATCH, DELETE]

• POST/PATCH body request example:
{
    "code": 10,
    "description": "Lorem ipsum dolor sit amet.",
    "bar_code": 78949007000155,
    "gross_weight": 13.0,
    "net_weight": 12.05
}
```

• To access ```customers```, ex:
```
• Endpoint: http://localhost:8080/customers

• Methods: [GET, POST, PATCH, DELETE]

• POST/PATCH body request example:
{
    "code": 10,
    "name": "John Doe",
    "nickname": "John Doe's corp.",
    "document": "91711805000107",
    "address": "7404 Hayes Burgs, Apt. 836, 82527-9356, West Jersey, Utah, Estados Unidos"
}
```