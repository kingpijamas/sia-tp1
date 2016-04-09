package ar.itba.edu.sia.tp1.utils;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuRule;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.gps.engine.GPSNode;
import ar.itba.edu.sia.tp1.gps.engine.GPSSolution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by scamisay on 09/04/16.
 */
public class CalcudokuJSPrinter {

    public String printJS(GPSSolution<CalcudokuRule, CalcudokuState> solution, int n) {
        StringBuffer aSolution = new StringBuffer();
        aSolution.append("var ");
        aSolution.append(structureForDraw(solution, n));
        aSolution.append("\n\nvar solutionPath = ");
        aSolution.append(drawCalcudokuPath(solution));
        aSolution.append(String.format("\n\nvar n = %d;",n));

        return aSolution.toString().replaceAll("n-","nL");
    }

    private String structureForDraw(GPSSolution<CalcudokuRule, CalcudokuState> solution, int n) {
        StringBuffer sb = new StringBuffer();
        int radio = 25;
        int circleDiameter = 2*radio + 10;
        int width = circleDiameter + n * 50;
        int height = n*50;
        List<String> nodeVariableNames = new ArrayList<>();
        nodeVariableNames.add("config");
        Iterator<GPSNode<CalcudokuRule, CalcudokuState>> it = solution.getPath().iterator();
        while (it.hasNext()){
            GPSNode<CalcudokuRule, CalcudokuState> node = it.next();
            CalcudokuRule swap = node.getRule();
            String stateId = "n"+node.getState().hashCode();
            if(swap != null){

                int parent = node.getState().apply(node.getRule()).hashCode();

                sb.append(String.format(",\n %s = { parent : n%d , innerHTML: '<canvas id=\"%s\" width=\"%d\" height=\"%d\"/>'}"
                        ,stateId, parent, stateId, width, height ));
            }else{
                sb.append(String.format("%s = { innerHTML: '<canvas id=\"%s\" width=\"%d\" height=\"%d\"/>'}"
                        ,stateId, stateId, width, height));
            }

            nodeVariableNames.add(String.format("%s",stateId));
        }

        sb.append("\n, chart_config = "+nodeVariableNames.toString()+";");
        sb.append(String.format("\nvar r = %d;",radio));
        //chart_config = [config, malory, lana, figgs, sterling, woodhouse, pseudo, pam, cheryl];

        return sb.toString().replaceAll("n-","nL");
    }

    private List<String> drawCalcudokuPath(GPSSolution<CalcudokuRule, CalcudokuState> solution) {
        List<String> list = new ArrayList<>();

        Iterator<GPSNode<CalcudokuRule, CalcudokuState>> it = solution.getPath().iterator();
        while (it.hasNext()){
            GPSNode<CalcudokuRule, CalcudokuState> node = it.next();
            String values = node.getState().getBoard().getAllValues().toString();
            CalcudokuRule swap = node.getRule();
            String stateId = ("n"+node.getState().hashCode()).replaceAll("n-","nL");
            String nodeInfo = null;
            if(swap != null){

                int hStar = solution.getCost() - node.getGValue();
                String functionValues = String.format("functionValues: {g: %d, h: %d, hStar: %d}"
                        , node.getGValue(), node.getHValue(), hStar);

                String swapStr = String.format("swap:{i0: %d, j0: %d, i1: %d, j1: %d}\n",swap.getFrom().getRow(),swap.getFrom().getCol()
                        , swap.getTo().getRow(), swap.getTo().getCol());

                int parent = node.getState().apply(node.getRule()).hashCode();

                nodeInfo = String.format("{id : '%s',values: %s , %s, parent : n%d , %s}\n",stateId, values, swapStr, parent, functionValues);
            }else{
                nodeInfo = String.format("{ id : '%s', values : %s }",stateId, values);
            }
            list.add(nodeInfo);
        }

        return list;
    }


}
