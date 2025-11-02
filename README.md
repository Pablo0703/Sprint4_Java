🏍️ Mottu Control – Projeto FIAP (Java, Render & Oracle)

Tecnologias: Java | Spring Boot | Oracle SQL Developer | Flyway | Docker | Render | GitHub

📽️ Vídeo de Apresentação:
➡️ Assista no YouTube
 (adicione o link da sua apresentação aqui)

🌐 Deploy da Aplicação:
🔗 https://sprint-4-java.onrender.com

📘 1. Descrição da Solução

O Mottu Control é uma API RESTful desenvolvida em Java com Spring Boot, criada para gerenciar o cadastro de motocicletas da empresa Mottu.
O sistema oferece operações CRUD completas (criação, leitura, atualização e exclusão) e foi desenvolvido integralmente no IntelliJ IDEA, versionado via GitHub e implantado no Render.

O banco de dados utilizado é o Oracle SQL Developer, garantindo robustez e confiabilidade nas transações e persistência de dados.

💡 2. Benefícios para o Negócio

✅ Centralização dos dados: Gerenciamento completo das motocicletas em um só sistema.

⚡ Agilidade: Permite o cadastro e consulta rápida de motos.

☁️ Escalabilidade em nuvem: Aplicação hospedada no Render com deploy automático via GitHub.

🔒 Segurança e integridade: Conexão segura com Oracle e uso de variáveis de ambiente.

🚀 Evolutivo: Estrutura pronta para expansão de novas entidades e funcionalidades futuras.

🧰 3. Dependências Utilizadas

As principais dependências adicionadas ao projeto incluem:

Spring Boot DevTools

Lombok

Spring Web

Spring HATEOAS

Spring WebServices

Thymeleaf

Spring Security

JDBC API

Spring Data JPA

Flyway Migration

Oracle Driver

PostgreSQL Driver

Validation

CycloneDX SBOM Support

Essas dependências garantem uma aplicação completa, segura e com suporte a boas práticas de desenvolvimento.

⚙️ 4. Estrutura do Projeto

A aplicação foi totalmente desenvolvida no IntelliJ IDEA, organizada de forma modular e seguindo o padrão MVC (Model–View–Controller), o que facilita a manutenção, escalabilidade e legibilidade do código.

Principais pacotes e responsabilidades:

Controller → Recebe as requisições HTTP.

DTO → Transfere dados entre camadas.

Entity → Representa tabelas do Oracle (via JPA).

Repository → Comunicação com o banco de dados.

Service → Contém regras de negócio.

Security → Gerencia autenticação/autorização (Spring Security).

resources/static → Arquivos CSS e JS.

resources/templates → Páginas HTML com Thymeleaf.

db.migration → Scripts SQL versionados com Flyway.

test → Testes unitários e de integração.

(Estrutura completa detalhada conforme seu projeto no IntelliJ foi mantida no documento principal.)

🔑 5. Variáveis de Ambiente
Variável	Descrição	Exemplo
DB_URL	String de conexão com Oracle	jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
DB_USER	Usuário do banco Oracle	rm556834
DB_PASSWORD	Senha do banco Oracle	********
SPRING_PROFILES_ACTIVE	Perfil ativo do Spring	prod
🚀 6. Deploy via Render

A aplicação é implantada automaticamente no Render, sendo reconstruída e publicada a cada push no GitHub.
O Render realiza o build via Maven e executa o .jar da aplicação com as variáveis configuradas no painel do serviço.

Exemplo de configuração:
Build Command: mvn clean package -DskipTests
Start Command: java -jar target/challenge-0.0.1-SNAPSHOT.jar


🌐 Aplicação online:
🔗 https://sprint-4-java.onrender.com

🧪 7. Testes da API (via Postman)
POST – Criar Moto
POST https://sprint-4-java.onrender.com/api/motos

{
  "modelo": "Honda Pop 110i",
  "placa": "BRA2E19",
  "ano": 2025
}


→ 201 Created

GET – Listar Todas
GET https://sprint-4-java.onrender.com/api/motos


→ 200 OK

PUT – Atualizar
PUT https://sprint-4-java.onrender.com/api/motos/1

{
  "modelo": "Honda Pop 110i EX",
  "placa": "BRA2E19",
  "ano": 2026
}


→ 200 OK

DELETE – Excluir
DELETE https://sprint-4-java.onrender.com/api/motos/1


→ 204 No Content

🧾 8. Conexão com o Banco Oracle SQL Developer
Campo	Valor
Host/SID:	oracle.fiap.com.br
Porta:	1521
Service Name:	ORCL
Usuário:	rm556834
Senha:	(definida localmente)
👥 9. Equipe de Desenvolvimento
Nome	RM
Pablo Lopes Doria de Andrade	556834
Vinicius Leopoldino de Oliveira	557047
