# predictionApp
      Una aplicación de predicción de clima sobre una galaxia muy muy lejana
      La misma se encuentra "deployada" en http://galaxyprediction.herokuapp.com/


Aplicación para la predicción climatica en una Galaxia con tres planetas.

Para acceder a la información se encuentra disponible la siguiente API:

# Para saber el cima en un día determinado.

  http://galaxyprediction.herokuapp.com/clima?dia={dayNumber}
  
  con dayNumber un dia en formato numerico, por ej, http://galaxyprediction.herokuapp.com/clima?dia=252 y la respuesta será de la forma : 
    {"day":252,"intensityType":"HIGH","clima":"RAIN"}
  
  que indica el día consultado, el tipo de intensidad climática y el clima.
  
# Para saber Cuántos periodos de sequía habrá: 

    http://galaxyprediction.herokuapp.com/dryDays
  
  Obteniendo como respuesta : {"dryDays":"489}
  
# Para saber Cuántos periodos de lluvia habrá :
    http://galaxyprediction.herokuapp.com/rainyDays
  
  Obteniendo como respuesta : {"rainyDays":"292}
  
# Para saber Cuántos períodos de lluvia con intesidad máxima habrá :
    http://galaxyprediction.herokuapp.com/maxRainyDays
  
 Obteniendo como respuesta una lista de dias con la misma intensidad de lluvia la cual es máxima:
 
  Ejemplo: {"day":3132,"intensityType":"HIGH","clima":"RAIN"},{"day":3492,"intensityType":"HIGH","clima":"RAIN"}]
  
# Para saber Cuántos períodos de condiciones óptimas de presión y temperatura habrá:

    http://galaxyprediction.herokuapp.com/optimalDays
  
  Obteniendo como respuesta :  {"optimalDays":"1353}
  
  
 # Para ejecutar el código localmente, hacer un clone del repo y ejecutar
 
      mvn clean install
      mvn package
      ejecutar el jar que esta en /target: java - jar predictionApp-rest-0.0.1-SNAPSHOT.jar
  
  
