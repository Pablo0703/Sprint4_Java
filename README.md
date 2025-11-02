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

🏗️ 3. Arquitetura da Solução

Fluxo simplificado da aplicação:

+------------------+       +----------------+       +--------------------+
| Desenvolvedor    |  ->   | GitHub Repo    |  ->   | Render (Deploy CI) |
| (IntelliJ IDEA)  |       | Código-fonte   |       | Build & Execução   |
+------------------+       +----------------+       +--------------------+
                                                |
                                                v
                                     +------------------------+
                                     | Oracle SQL Developer DB |
                                     +------------------------+

🧰 4. Dependências Utilizadas

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

⚙️ 5. Estrutura do Projeto

A aplicação foi totalmente desenvolvida no IntelliJ IDEA, organizada de forma modular e seguindo o padrão MVC (Model–View–Controller), o que facilita a manutenção, escalabilidade e a legibilidade do código.

A seguir está a estrutura completa do projeto:

📦 Sprint4_Java-main
 ┣ 📂 .mvn
 ┣ 📂 src
 ┃ ┣ 📂 main
 ┃ ┃ ┣ 📂 java
 ┃ ┃ ┃ ┗ 📂 br.com.fiap.Challenge
 ┃ ┃ ┃   ┣ 📂 Controller
 ┃ ┃ ┃   ┃ ┣ AuthController.java
 ┃ ┃ ┃   ┃ ┣ EnderecoController.java
 ┃ ┃ ┃   ┃ ┣ FilialController.java
 ┃ ┃ ┃   ┃ ┣ HistoricoLocalizacaoController.java
 ┃ ┃ ┃   ┃ ┣ HomeController.java
 ┃ ┃ ┃   ┃ ┣ LocalizacaoMotoController.java
 ┃ ┃ ┃   ┃ ┣ MotociclistaController.java
 ┃ ┃ ┃   ┃ ┣ MotoController.java
 ┃ ┃ ┃   ┃ ┣ NotaFiscalController.java
 ┃ ┃ ┃   ┃ ┣ PatioController.java
 ┃ ┃ ┃   ┃ ┣ SignupController.java
 ┃ ┃ ┃   ┃ ┣ StatusMotoController.java
 ┃ ┃ ┃   ┃ ┣ StatusOperacaoController.java
 ┃ ┃ ┃   ┃ ┣ TipoMotoController.java
 ┃ ┃ ┃   ┃ ┗ ZonaPatioController.java
 ┃ ┃ ┃   ┣ 📂 DTO
 ┃ ┃ ┃   ┃ ┣ EnderecoDTO.java
 ┃ ┃ ┃   ┃ ┣ FilialDTO.java
 ┃ ┃ ┃   ┃ ┣ HistoricoLocalizacaoDTO.java
 ┃ ┃ ┃   ┃ ┣ LocalizacaoMotoDTO.java
 ┃ ┃ ┃   ┃ ┣ MotociclistaDTO.java
 ┃ ┃ ┃   ┃ ┣ MotoDTO.java
 ┃ ┃ ┃   ┃ ┣ NotaFiscalDTO.java
 ┃ ┃ ┃   ┃ ┣ PatioDTO.java
 ┃ ┃ ┃   ┃ ┣ RegisterRequestDTO.java
 ┃ ┃ ┃   ┃ ┣ StatusMotoDTO.java
 ┃ ┃ ┃   ┃ ┣ StatusOperacaoDTO.java
 ┃ ┃ ┃   ┃ ┣ TipoMotoDTO.java
 ┃ ┃ ┃   ┃ ┗ ZonaPatioDTO.java
 ┃ ┃ ┃   ┣ 📂 Entity
 ┃ ┃ ┃   ┃ ┣ EnderecoEntity.java
 ┃ ┃ ┃   ┃ ┣ FilialEntity.java
 ┃ ┃ ┃   ┃ ┣ HistoricoLocalizacaoEntity.java
 ┃ ┃ ┃   ┃ ┣ LocalizacaoMotoEntity.java
 ┃ ┃ ┃   ┃ ┣ MotociclistaEntity.java
 ┃ ┃ ┃   ┃ ┣ MotoEntity.java
 ┃ ┃ ┃   ┃ ┣ NotaFiscalEntity.java
 ┃ ┃ ┃   ┃ ┣ PatioEntity.java
 ┃ ┃ ┃   ┃ ┣ PerfilEntity.java
 ┃ ┃ ┃   ┃ ┣ StatusMotoEntity.java
 ┃ ┃ ┃   ┃ ┣ StatusOperacaoEntity.java
 ┃ ┃ ┃   ┃ ┣ TipoMotoEntity.java
 ┃ ┃ ┃   ┃ ┣ UsuarioEntity.java
 ┃ ┃ ┃   ┃ ┗ ZonaPatioEntity.java
 ┃ ┃ ┃   ┣ 📂 Repository
 ┃ ┃ ┃   ┃ ┣ EnderecoRepository.java
 ┃ ┃ ┃   ┃ ┣ FilialRepository.java
 ┃ ┃ ┃   ┃ ┣ HistoricoLocalizacaoRepository.java
 ┃ ┃ ┃   ┃ ┣ LocalizacaoMotoRepository.java
 ┃ ┃ ┃   ┃ ┣ MotociclistaRepository.java
 ┃ ┃ ┃   ┃ ┣ MotoRepository.java
 ┃ ┃ ┃   ┃ ┣ NotaFiscalRepository.java
 ┃ ┃ ┃   ┃ ┣ PatioRepository.java
 ┃ ┃ ┃   ┃ ┣ PerfilRepository.java
 ┃ ┃ ┃   ┃ ┣ StatusMotoRepository.java
 ┃ ┃ ┃   ┃ ┣ StatusOperacaoRepository.java
 ┃ ┃ ┃   ┃ ┣ TipoMotoRepository.java
 ┃ ┃ ┃   ┃ ┣ UsuarioRepository.java
 ┃ ┃ ┃   ┃ ┗ ZonaPatioRepository.java
 ┃ ┃ ┃   ┣ 📂 Security
 ┃ ┃ ┃   ┃ ┣ CustomUserDetailsService.java
 ┃ ┃ ┃   ┃ ┗ SecurityConfig.java
 ┃ ┃ ┃   ┣ 📂 Service
 ┃ ┃ ┃   ┃ ┣ EnderecoService.java
 ┃ ┃ ┃   ┃ ┣ FilialService.java
 ┃ ┃ ┃   ┃ ┣ HistoricoLocalizacaoService.java
 ┃ ┃ ┃   ┃ ┣ LocalizacaoMotoService.java
 ┃ ┃ ┃   ┃ ┣ MotociclistaService.java
 ┃ ┃ ┃   ┃ ┣ MotoService.java
 ┃ ┃ ┃   ┃ ┣ NotaFiscalService.java
 ┃ ┃ ┃   ┃ ┣ PatioService.java
 ┃ ┃ ┃   ┃ ┣ StatusMotoService.java
 ┃ ┃ ┃   ┃ ┣ StatusOperacaoService.java
 ┃ ┃ ┃   ┃ ┣ TipoMotoService.java
 ┃ ┃ ┃   ┃ ┣ UsuarioService.java
 ┃ ┃ ┃   ┃ ┗ ZonaPatioService.java
 ┃ ┃ ┃   ┗ 📜 ChallengeApplication.java
 ┃ ┃ ┗ 📂 resources
 ┃ ┃   ┣ 📂 db.migration
 ┃ ┃   ┃ ┣ V1_create_auth_tables.sql
 ┃ ┃   ┃ ┣ V2_insert_roles.sql
 ┃ ┃   ┃ ┣ V3_insert_admin_user.sql
 ┃ ┃   ┃ ┣ V4_insert_sample_users.sql
 ┃ ┃   ┃ ┣ V5_add_name_email.sql
 ┃ ┃   ┃ ┣ V6_update_existing_users.sql
 ┃ ┃   ┃ ┗ V7_set_not_null.sql
 ┃ ┃   ┣ 📂 static
 ┃ ┃   ┃ ┣ 📂 css
 ┃ ┃   ┃ ┃ ┣ global.css
 ┃ ┃   ┃ ┃ ┣ home.css
 ┃ ┃   ┃ ┃ ┣ login.css
 ┃ ┃   ┃ ┃ ┣ menu.css
 ┃ ┃   ┃ ┃ ┗ signup.css
 ┃ ┃   ┃ ┗ 📂 js
 ┃ ┃   ┃   ┗ signup.js
 ┃ ┃   ┣ 📂 templates
 ┃ ┃   ┃ ┣ 📂 fragments
 ┃ ┃   ┃ ┃ ┣ footer.html
 ┃ ┃   ┃ ┃ ┣ header.html
 ┃ ┃   ┃ ┃ ┗ menu.html
 ┃ ┃   ┃ ┣ 📂 Endereco
 ┃ ┃   ┃ ┃ ┣ FormularioEndereco.html
 ┃ ┃   ┃ ┃ ┗ listar.html
 ┃ ┃   ┃ ┣ 📂 Filial
 ┃ ┃   ┃ ┃ ┣ FormularioFilial.html
 ┃ ┃   ┃ ┃ ┗ listar.html
 ┃ ┃   ┃ ┣ 📂 HistoricoLocalizacao
 ┃ ┃   ┃ ┃ ┣ formulario.html
 ┃ ┃   ┃ ┃ ┗ listar.html
 ┃ ┃   ┃ ┣ 📂 LocalizacaoMoto
 ┃ ┃   ┃ ┃ ┣ FormularioLocalizacaoMoto.html
 ┃ ┃   ┃ ┃ ┗ listar.html
 ┃ ┃   ┃ ┣ 📂 Moto
 ┃ ┃   ┃ ┃ ┣ FormularioMoto.html
 ┃ ┃   ┃ ┃ ┗ listar.html
 ┃ ┃   ┃ ┣ 📂 Motociclista
 ┃ ┃   ┃ ┃ ┣ FormularioMotociclista.html
 ┃ ┃   ┃ ┃ ┗ listar.html
 ┃ ┃   ┃ ┣ 📂 NotaFiscal
 ┃ ┃   ┃ ┃ ┣ FormularioNotaFiscal.html
 ┃ ┃   ┃ ┃ ┗ listar.html
 ┃ ┃   ┃ ┣ 📂 Patio
 ┃ ┃   ┃ ┃ ┣ FormularioPatio.html
 ┃ ┃   ┃ ┃ ┗ listar.html
 ┃ ┃   ┃ ┣ 📂 StatusMoto
 ┃ ┃   ┃ ┃ ┣ FormularioStatusMoto.html
 ┃ ┃   ┃ ┃ ┗ listar.html
 ┃ ┃   ┃ ┣ 📂 StatusOperacao
 ┃ ┃   ┃ ┃ ┣ FormularioStatusOperacao.html
 ┃ ┃   ┃ ┃ ┗ listar.html
 ┃ ┃   ┃ ┣ 📂 TipoMoto
 ┃ ┃   ┃ ┃ ┣ FormularioTipoMoto.html
 ┃ ┃   ┃ ┃ ┗ listar.html
 ┃ ┃   ┃ ┣ 📂 ZonaPatio
 ┃ ┃   ┃ ┃ ┣ FormularioZonaPatio.html
 ┃ ┃   ┃ ┃ ┗ listar.html
 ┃ ┃   ┃ ┣ home.html
 ┃ ┃   ┃ ┣ login.html
 ┃ ┃   ┃ ┗ signup.html
 ┃ ┃   ┗ 📜 application.properties
 ┃ ┗ 📂 test
 ┃   ┗ 📂 java/br/com/fiap/Challenge
 ┃     ┣ 📂 Controller
 ┃     ┃ ┣ (Testes unitários de todos os Controllers)
 ┃     ┣ 📂 Service
 ┃     ┃ ┣ (Testes unitários de todos os Services)
 ┃     ┗ 📜 ChallengeApplicationTests.java
 ┗ 📜 pom.xml

📁 Resumo das camadas

Controller: Responsável por receber as requisições HTTP e direcioná-las às camadas de serviço.

DTO (Data Transfer Object): Estruturas para trafegar dados entre camadas de forma eficiente.

Entity: Representação das tabelas do banco Oracle em classes Java (JPA).

Repository: Interfaces que fazem a comunicação com o banco via JPA.

Service: Contém as regras de negócio da aplicação.

Security: Gerencia autenticação e autorização (Spring Security).

Resources/static: Arquivos estáticos (CSS, JS).

Resources/templates: Páginas HTML com integração Thymeleaf.

db.migration: Scripts SQL versionados com Flyway.

test: Testes unitários e de integração com JUnit e Mockito.

🔑 6. Variáveis de Ambiente
Variável	Descrição	Exemplo
DB_URL	String de conexão com Oracle	jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
DB_USER	Usuário do banco Oracle	rm556834
DB_PASSWORD	Senha do banco Oracle	********
SPRING_PROFILES_ACTIVE	Perfil ativo do Spring	prod
🚀 7. Deploy via Render

A aplicação é implantada automaticamente no Render, sendo reconstruída e publicada a cada push no GitHub.
O Render realiza o build via Maven e executa o .jar da aplicação com as variáveis configuradas no painel do serviço.

Exemplo de configuração no Render:
Build Command: mvn clean package -DskipTests
Start Command: java -jar target/challenge-0.0.1-SNAPSHOT.jar


🌐 Aplicação online:
🔗 https://sprint-4-java.onrender.com

🧪 8. Testes da API (via Postman)
POST – Criar Moto
POST https://sprint-4-java.onrender.com/api/motos
Body:
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
Body:
{
  "modelo": "Honda Pop 110i EX",
  "placa": "BRA2E19",
  "ano": 2026
}
→ 200 OK

DELETE – Excluir
DELETE https://sprint-4-java.onrender.com/api/motos/1
→ 204 No Content

🧾 9. Conexão com o Banco Oracle SQL Developer

Para acessar o banco via SQL Developer:

Campo	Valor
Host/SID:	oracle.fiap.com.br
Porta:	1521
Service Name:	ORCL
Usuário:	rm556834
Senha:	(definida localmente)

👥 10. Equipe de Desenvolvimento
Nome	RM
Pablo Lopes Doria de Andrade	556834
Vinicius Leopoldino de Oliveira	557047
