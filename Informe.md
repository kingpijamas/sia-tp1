# SIA- TP1- GRUPO 7
% Juan Pablo Rey, Santiago Camisay, Fernando Bejarano

#Problema asignado: CalcuDoku


## Heuristicas (función h):
+ piso o techo de {min {max(filas,grupos)/2,max(columnas,grupos)/2}}


## Estructuras de datos implementados en el problema

## Estructura de un tablero(Board).
### Estructura de un estado (CalcudokuState)
+ la matriz de bits que representa el tablero.
+ lista que contiene la representación en bits(almacenada en bitset) de cada numero que se puede emplear.
+ lista de grupos. Las misma es final y no se copia cuando se copian estados (se comparte, no tiene sentido copiarlos por deep copy):
### Estructura de un grupo
+ result
+ operador (MINUS,PLUS,MULTIPLY,DIVIDE,IDENTITY); "Identity" es simplemente el valor resultado.Este valor es un enum
+ Lista de posiciones (i,j) de casilleros que forman el grupo.


## Reglas

Se swapea cualquiera con cualquiera.s Las reglas se crean una sola vez al comienzo.Se descartan reglas simetricas.

## Llenado del tablero
Se llena todo el tablero por fila en forma aleatoria. Se completa numeros del 1 al N en primer fila ordenadas al azar, en la siguiente del 2 al N y por ultimo el 1.. ESTO ES ACEPTABLE POR LA CATEDRA.
Luego swapeos por fila solo.

## Parser
Para generar el tablero inicial, parsea  un string y devuelve el estado inicial. El estado inicial tiene el bitset en cero todo.



## Algoritmos de búsqueda
### desinformadas (no usan la función heuristica).
	*bfs
	*dfs
### informadas ( usan la funcion heuristica).
	* A*.[listo.]
	* iterativo (IDDFS): en gpsengine.getComparator hacer lo mismo que en dfs. Luego en GPSSolutionProcess.explode hacer que cuando se levanta el nodo de mayor costo,si su costo es k parar! Los datos que se tenían se tiran (version pura del algoritmo), y se hace todo de nuevo para el siguiente valor de k. Aumentar k de a una unidad


