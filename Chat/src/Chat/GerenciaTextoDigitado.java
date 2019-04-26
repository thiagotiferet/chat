package Chat;

public class GerenciaTextoDigitado {
    String auxiliar = "";
    String temporario, queue_name_second, exchange_name;
    String destinatario = "";
    boolean escolha = true; 
    int escolhido;
    
    public void gerenciaTexto(String texto){
        if (texto.charAt(0) == '@'){
            gerenciaDestinatario(texto);
        }
        else{
            if (texto.charAt(0) == '!'){
                gerenciaComando(texto);
            }
            else{
                if (escolha == false){
                    escolhido = 5;
                }
                else{
                    escolhido = 6;
                }
            }
        }
    }
    
    public void gerenciaDestinatario(String texto){
        if (texto.charAt(1) == '@'){
            auxiliar = texto.substring(2, texto.length()) + "(GRUPO)";
            queue_name_second = texto.substring(2, texto.length());
            escolha = false;
            destinatario = auxiliar;
        }
        else{
            auxiliar = texto.substring(1, texto.length());
            queue_name_second = texto.substring(1, texto.length());
            escolha = true;
            destinatario = auxiliar;
        }
    } 
    
    public void gerenciaComando(String texto){
        String[] aux = texto.split(" ");
        auxiliar = aux[0];
            if(auxiliar.equals("!addGroup")){
                exchange_name = aux[1];
                escolhido = 1;
            }
            else{
                if(auxiliar.equals("!delGroup")){
                    exchange_name = aux[1];
                    escolhido = 4;
                }
                else{
                    if(auxiliar.equals("!addUserToGroup")){ 
                        temporario = aux[1];
                        exchange_name = aux[2];
                        escolhido = 2;
                    }
                    else{
                        if(auxiliar.equals("!delUserFromGroup")){
                            temporario = aux[1];
                            exchange_name = aux[2];
                            escolhido = 3;
                        }
                        else{
                            escolhido = 7;
                        }
                    } 
                }
            }
    }
    
}