package controllers;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSetFormatter;
import play.*;
import play.mvc.*;

import views.html.*;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    //public Result index() {
    //  return ok(index.render("Your new application is ready."));
    //}
    public Result index(String unsur) {
        //return ok(index.render("Your new application is ready."));

        QueryExecution qe = QueryExecutionFactory.sparqlService("http://localhost:3030/PeriodicTable/query", "PREFIX group: <http://www.daml.org/2003/01/periodictable/PeriodicTable#> SELECT ?a ?b ?c ?y ?x ?z ?d ?e ?f WHERE {group:"+unsur+" group:atomicNumber ?y . group:"+unsur+" group:atomicWeight ?x . group:"+unsur+" group:casRegistryID ?z . group:"+unsur+" group:name ?a . group:"+unsur+" group:symbol ?b . group:"+unsur+" group:color ?c . group:"+unsur+" group:proton ?d . group:"+unsur+" group:electron ?e . group:"+unsur+" group:neutron ?f}");
        org.apache.jena.query.ResultSet results = qe.execSelect();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, results);
        String json = new String(outputStream.toByteArray());
        qe.close();
        return ok(json);
    }

    public Result periodiks(String unsur) {
        //return ok(index.render("Your new application is ready."));

        QueryExecution qe = QueryExecutionFactory.sparqlService("http://localhost:3030/PeriodicTable/query", "PREFIX group: <http://www.daml.org/2003/01/periodictable/PeriodicTable#> SELECT ?name ?symbol ?color ?atomicNumber ?atomicWeight ?casRegistryID ?proton ?electron ?neutron WHERE {group:"+unsur+" group:atomicNumber ?atomicNumber . group:"+unsur+" group:atomicWeight ?atomicWeight . group:"+unsur+" group:casRegistryID ?casRegistryID . group:"+unsur+" group:name ?name . group:"+unsur+" group:symbol ?symbol . group:"+unsur+" group:color ?color . group:"+unsur+" group:proton ?proton . group:"+unsur+" group:electron ?electron . group:"+unsur+" group:neutron ?neutron}");
        org.apache.jena.query.ResultSet results = qe.execSelect();

        List<String> variable = results.getResultVars();

        List<String> result = new ArrayList<>();
        System.out.println(variable);
        QuerySolution data_res = results.next();
        for (int i = 0; i < variable.size(); i++) {
            result.add(String.valueOf(data_res.get(String.valueOf(variable.get(i)))));
            System.out.println(i);
            System.out.println(variable.get(i));
            System.out.println(data_res.get(String.valueOf(variable.get(i))));
        }
        return ok(periodik.render(data_res, variable));
    }

    public Result search() {
        return ok(form.render());
    }
}
