# chat
chat em linha de comando

Cliente de chat do tipo linha de comando usando o RabbitMQ como servidor de mensagens.
Para ter acesso a um servidor de mensagem, deve-se criar uma conta gratuita no serviço CloudAMQP:
    https://www.cloudamqp.com/plans.html
 
Interface do Chat
Ao ser executado, o chat pergunta o nome do usuário do mesmo. Exemplo:
 
User:

Com isso, o usuário digita o seu nome de usuário. Exemplo:
 
User: thiagooliveira

Com o nome do usuário, o chat exibe um prompt para que o usuário inicie a comunicação. Exemplo de prompt:
 
>>
 
No prompt, se o usuário (thiagooliveira) quer conversar com uma outro usuário do chat, ele deve digitar "@" seguido do nome do usuário com o qual ele quer conversar. Exemplo:
 
>> @joaosantos

Com isso, o prompt é alterado para exibir o nome do outro usuário com o qual se quer enviar mensagem. Exemplo:
 
joaosantos>> 

Nesse exemplo, toda nova mensagem digitada no prompt é enviada para "joaosantos" até que o usuário mude para para um novo destinatário. Exemplo:
 
joaosantos>> Olá, João!!!
joaosantos>> Vamos adiantar o trabalho?

Por exemplo, se o usuário quiser enviar mensagens para outro usuário diferente de "joaosantos", ele deve informar o nome do outro usuário para o qual ele quer enviar mensagem:
 
joaosantos>> @marciocosta
 
O comando acima faria o prompt ser "chaveado" para "marciocosta". Com isso, as próximas mensagens seriam enviadas para o usuário "marciocosta":
 
marciocosta>> Oi, Marcio!!
marciocosta>> Vamos sair hoje?
marciocosta>> Já estou em casa!
marciocosta>>

A qualquer momento, o usuário (exemplo: thiagooliveira) pode receber mensagem de qualquer outro usuário (marciocosta, joaosantos...). Nesse caso, a mensagem é impressa na tela da seguinte forma:
 
(21/02/2017 às 20:53) marciocosta diz: E aí, Thiago! Vamos sim!

Depois de impressa a mensagem, o prompt volta para o estado anterior:
 
marciocosta>> 
  
O chat tambem disponibiliza comandos de criação de grupos e de adição de membros a um grupo.
Para criar um novo grupo, o usuario pode digitar na linha de comando do chat o simbolo "!" seguido do nome do comando "addGroup" seguido do nome do grupo que se deseja criar. Exemplo de criacao de um grupo chamado "amigos":
 
marciocosta>> !addGroup amigos
marciocosta>>
 
Apesar, de nesse exemplo anterior, o usuário estar em uma seção de envio de mensagens para "marciocosta", o chat será capaz de identificar que a entrada "!addGroup amigos" não se trata de uma mensagem a ser enviada ao usuário "marciocosta" e sim um comando ao chat, pelo fato de se iniciar com o simbolo "!". Toda entrada iniciada com "!" é tratada pelo chat como um comando.
 
Para incluir um usuário em um grupo deve-se usar o comando "addUserToGroup" seguido dos parametros nome do usuario e nome do grupo. Exemplo onde se adiciona os usuários "marciocosta" e "joaosantos" ao grupo amigos:
 
marciocosta>> !addUserToGroup joaosantos amigos
marciocosta>> !addUserToGroup marciocosta amigos
marciocosta>>
 
O usuário que cia um grupo é adicionado automaticamente ao mesmo grupo. Por exemplo, se considerarmos que foi o usuário "thiagooliveira" que criou o grupo "amigos", "thiagooliveira" é adicionado  automaticamente ao grupo amigos
 
No prompt, se o usuário (thiagooliveira) quer enviar mensagem para um grupo, ele deve digitar "@@" seguido do nome do grupo para o qual ele quer enviar mensagens. Depois que o usuário pressionar a tecla <ENTER>, o prompt é alterado para exibir o nome do grupo correspondente e a indicação entre parêntesis de que se trata de um grupo. Exemplo:
 
marciocosta>> @@amigos
amigos(grupo)>>  
 
A partir disso, o usuário poderá digitar as mensagens para o respectivo grupo:
 
amigos(grupo)>> Olá, pessoal!
amigos(grupo)>> Alguém vai ao show?
amigos(grupo)>>
  
Também existem comandos para excluir um grupo e remover um usuário do grupo.Para remover um usuário de um determinado grupo, deve-se usar o comando "delUserFromGroup" seguido do <nome do usuário> e do <nome do grupo>. Exemplo:
 
marciocosta>> !delFromGroup joaosantos amigos
marciocosta>>
 
Neste último exemplo, joaosantos é removido do grupo amigos.
Para excluir um grupo, deve-se usar o comando "delGroup" seguido do <nome do grupo> a ser removido. Exemplo:
 
marciocosta> !delGroup amigos
marciocosta>
