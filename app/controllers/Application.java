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
//    public Result index(String unsur) {
//        //return ok(index.render("Your new application is ready."));
//
//        QueryExecution qe = QueryExecutionFactory.sparqlService("http://localhost:3030/PeriodicTable/query", "PREFIX group: <http://www.daml.org/2003/01/periodictable/PeriodicTable#> SELECT ?a ?b ?c ?y ?x ?z ?d ?e ?f WHERE {group:"+unsur+" group:atomicNumber ?y . group:"+unsur+" group:atomicWeight ?x . group:"+unsur+" group:casRegistryID ?z . group:"+unsur+" group:name ?a . group:"+unsur+" group:symbol ?b . group:"+unsur+" group:color ?c . group:"+unsur+" group:proton ?d . group:"+unsur+" group:electron ?e . group:"+unsur+" group:neutron ?f}");
//        org.apache.jena.query.ResultSet results = qe.execSelect();
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ResultSetFormatter.outputAsJSON(outputStream, results);
//        String json = new String(outputStream.toByteArray());
//        qe.close();
//        return ok(json);
//    }

    public Result periodiks(String unsur) {
        //return ok(index.render("Your new application is ready."));

        QueryExecution qe = QueryExecutionFactory.sparqlService("http://localhost:3030/PeriodicTable/query", "PREFIX group: <http://www.daml.org/2003/01/periodictable/PeriodicTable#> SELECT ?name ?symbol ?color ?atomicNumber ?atomicWeight ?casRegistryID ?1s ?2s ?2p ?3s ?3p ?4s ?3d ?4p ?5s ?4d ?5p ?6s ?4f ?5d ?6p ?7s ?5f ?6d ?7p WHERE {group:"+unsur+" group:atomicNumber ?atomicNumber . group:"+unsur+" group:atomicWeight ?atomicWeight . group:"+unsur+" group:casRegistryID ?casRegistryID . group:"+unsur+" group:name ?name . group:"+unsur+" group:symbol ?symbol . group:"+unsur+" group:color ?color . group:"+unsur+" group:1s ?1s . group:"+unsur+" group:2s ?2s . group:"+unsur+" group:2p ?2p . group:"+unsur+" group:3s ?3s . group:"+unsur+" group:3p ?3p . group:"+unsur+" group:4s ?4s . group:"+unsur+" group:3d ?3d . group:"+unsur+" group:4p ?4p . group:"+unsur+" group:5s ?5s . group:"+unsur+" group:4d ?4d . group:"+unsur+" group:5p ?5p . group:"+unsur+" group:6s ?6s . group:"+unsur+" group:4f ?4f . group:"+unsur+" group:5d ?5d . group:"+unsur+" group:6p ?6p . group:"+unsur+" group:7s ?7s . group:"+unsur+" group:5f ?5f . group:"+unsur+" group:6d ?6d . group:"+unsur+" group:7p ?7p}");
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

    public Result periodikss(String unsur) {
        //return ok(index.render("Your new application is ready."));

        QueryExecution qe = QueryExecutionFactory.sparqlService("http://localhost:3030/PeriodicTable/query", "PREFIX group: <http://www.daml.org/2003/01/periodictable/PeriodicTable#> SELECT ?proton ?electron ?neutron WHERE {group:"+unsur+" group:proton ?proton . group:"+unsur+" group:electron ?electron . group:"+unsur+" group:neutron ?neutron}");
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

    public Result search()
    {
        return ok(form.render());
    }

    public Result home()
    {
        return ok(home.render());
    }

    public Result neutron()
    {
        return ok(neutron.render());
    }



}
