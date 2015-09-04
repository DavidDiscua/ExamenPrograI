/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pautaexamenproi;

import javax.swing.JOptionPane;

/**
 *
 * @author David Discua
 */
public class PautaExamenProI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String Opcion= " ";//Iniciamos nuestra variable que utilizaremos para llevar el control de las opciones.
        
        do{//utilizamos la estructura de control Do while para que siempre la primera vez 
           //ingrese al iniciar el programa.
        
            if(ValidacionMenu(Opcion=(JOptionPane.showInputDialog("1.Calcular complemento a 9-"
                                                  + "\n2.-Figura VE"
                                                  + "\n3.-Emparedado"
                                                  + "\n4.-Salir")))){
                
                    //procesamos los casos
                
                        if(Opcion.equals("1")){//entramos opción 1
                            
                            String Binario = "";
                            if(ValidacionNumeroBinario(Binario = JOptionPane.showInputDialog("Ingrese un número binario"
                                    + "no mayor a 00001001"))) {
                                
                                if(ValidacionNumeroBinarioNueve(Binario)){
                                
                                     JOptionPane.showMessageDialog(null, "Entrada invalida, asegurese de ingresar números\n"
                                              + "dentro del rango valido no mayor 00001001");
                                    
                                }else{//mensaje al usuario que ingreso un numero mayor a 00001001
                              
                                /*Proceso a realizar la opción ya que he pasado todos los filtros*/
                                    JOptionPane.showMessageDialog(null,"El complemento a Nueve es:\n"+ComplementoANueve(Binario));
                                }
                                
                            
                            }else {//mensaje al usuario indicando que no es un número binario
                        
                             JOptionPane.showMessageDialog(null, "No ha ingresado un número binario");
                            }
                            
                            
                        }else if(Opcion.equals("2")){//entramos opción 2
                        
                        
                            
                        }else if (Opcion.equals("3")){//opción por defecto , sabemos que es la opción 3
                        
                            String Frase = " ";
                            
                            if(ValidarEspaciosYNumeros(Frase=JOptionPane.showInputDialog("Ingrese una frase "
                                + "sin espacios que contenga la palabra pan"))){
                        
                                //Procedemos a realizar la opcion
                                JOptionPane.showMessageDialog(null,Emparedado(Frase));
                            }else{
                        
                                JOptionPane.showMessageDialog(null, "La frase no es valida");
                            }
                        }
                
            }else {//la opcion no es valida, enviamos mensaje al usuario
            
                
                JOptionPane.showMessageDialog(null, "Entrada invalida, asegurese de ingresar números\n"
                                              + "dentro del rango valido");
                
            }//fin else
            
            
        }while(!Opcion.equals("4"));
        
    }//fin metodo main
    
    
    private static boolean ValidacionMenu(String Opcion){//inicio validacion de entrada
    
        /*Esta función valida que en el menu de opciones solo se ingresen números
         */
           if(Opcion.matches("[0-9]{1}")){           
                return true;   
           }
           return false;
    }//Fin metodo validacion
    
    private static boolean ValidacionNumeroBinario(String Numero){
    
        /*Esta función valida que el numero ingresado esté en binario
         */
        
        if(Numero.matches("[0-1]{1,}")){
            return true;                        
        }        
        return false;
    }//fin metodo validar numero Binario
    
    private static boolean ValidacionNumeroBinarioNueve(String Numero){
    
    /*Está funcion valida que el número ingresado en binario no sea mayor a 9 es decir 0000-1001*/
       
       if(Integer.parseInt(Numero) >1001){
           
            return true;                        
        }        
        return false;
        
    }
    
    
    private static String ComplementoANueve(String Binario){
    /*Función que se encarga de calcular el complemento a nueve de un número binario*/
             
    return ConvertirABinario((ConvertirADecimal(Binario)-9));
    }//fin funcion complemento a nueve
    
    
    private static int ConvertirADecimal(String Binario){
        
    /*Esta función se encarga de convertir un numer binario a decimal*/        
        int Decimal = 0;    
      
    //Método de los pesos para convertir de Binario a decimal
    //Sabemos que los pesos de cada bit seran 1,2,4,8,16,32,64 etc...
    // y sumaremos los pesos de los bits encendidos es decir de los unos.
    //por ejemplo 9 en binario es 1001 haciendo los pesos [1,2,4,8], seria la suma de 1+8 debido a que son los bits encendidos
     
        String Temp="";
        //invertimos la cadena
           for (int i = Binario.length()-1; i >=0; i--)Temp+=Binario.charAt(i);
  
        Binario=Temp;
        for (int i = 0; i <Binario.length(); i++){    
            
            if(Binario.charAt(i)=='1'){
              
                Decimal +=Math.pow(2,i);
            }
        }
    return Decimal;
    }//fin funcion
    
    private static String ConvertirABinario(int Decimal){
        
    /*Función que convierte un número decimal a binario
     Utulizamos la tecnica de dividir entre dos y tomamos el residuo de la operacion*/
        String Binario = "";
       
        while (Decimal != 0){
        
            if(Decimal % 2 == 0)Binario = "0" + Binario;
            else Binario= "1" + Binario;        
            Decimal = Decimal / 2;
        }//fin while
    
        if(Binario.equals(""))Binario = "0";
   
        return Binario;
    }//fin funcion
    
    
    private static boolean ValidarEspaciosYNumeros(String Frase){
        /*Esta Función se encarga de verificar si existen espacios o numeros*/
    
        if(Frase.matches("[a-zA-Z]{1,}")){
            return true;
        }
        
    return false;
    }
    
    
    private static String Emparedado(String Frase){
    //funcion que verifica si tenemos un emparedado
        String Retorno = "";
        Frase = Frase.toLowerCase();
        String FraseR = Frase;
        String Buscando = "pan";
        /*Comprobamos que tengas un sandwich el cual consta al menos dos panes*/
        int c=0;
         while (Frase.indexOf(Buscando) > -1) {
              Frase = Frase.substring(Frase.indexOf(
                Buscando)+Buscando.length(),Frase.length());
              c++; 
          }
         
         if(c>1){
         //Tenemos un sandiwch , por ende sacaremos sus ingredientes en orden.
             //el Split nos permite hacer substring de una cadena segun el separador que indiquemos
             //en este caso es "pan", nos creara un arreglo donde cada posición del arreglo, corresponde
             //a un substring por ejemplo HOLAMUNDOpanADIOS, crearia un arreglo donde la posicion "0" es HOLAMUNDO
             // y la posicion "1" seria ADIOS
             String [] Ingredientes = FraseR.split("pan");
             for (int i = 0; i <Ingredientes.length; i++)
                 
                 if((i+1)<Ingredientes.length)Retorno+=Ingredientes[i]+"-";
                 else Retorno+=Ingredientes[i];//sirve para validar que si es el ultimo ingrediente no anexe "-"
            
            
         }else return "Emparedado INCOMPLETO";         
        
        return Retorno;
    }//fin metodo emparedaddos
    
}//fin clase main
