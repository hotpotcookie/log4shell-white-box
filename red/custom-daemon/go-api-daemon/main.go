package main
//----------
import (
    json    "encoding/json"
    fmt     "fmt"
    ioutil  "io/ioutil"
    log     "log"
    http    "net/http"
    mux     "github.com/gorilla/mux"
)
//----------
type property struct {
    HOST            string `json:"HOST"`
    SHELL           string `json:"SHELL"`    
    PORT_LISTENER   string `json:"PORT_LISTENER"`
    PORT_JAVA_HTTP  string `json:"PORT_JAVA_HTTP"`
}
type allProperties []property;
var properties = allProperties{
    {
        HOST:            "192.168.1.6",
        SHELL:           "/bin/bash",
        PORT_LISTENER:   "2200",
        PORT_JAVA_HTTP:  "4080",
    },
}
//----------
func getProperties(w http.ResponseWriter, r *http.Request) {
    json.NewEncoder(w).Encode(properties)
}

func updateProperties(w http.ResponseWriter, r *http.Request) {
    var updatedProperty property;
    reqBody, err := ioutil.ReadAll(r.Body);
    json.Unmarshal(reqBody, &updatedProperty);
    //----------
    for i, key := range properties {
        if len(updatedProperty.HOST) > 0 { key.HOST = updatedProperty.HOST; } else { key.HOST = key.HOST; }
        if len(updatedProperty.SHELL) > 0 { key.SHELL = updatedProperty.SHELL; } else { key.SHELL = key.SHELL; }
        if len(updatedProperty.PORT_LISTENER) > 0 { key.PORT_LISTENER = updatedProperty.PORT_LISTENER; } else { key.PORT_LISTENER = key.PORT_LISTENER; }
        if len(updatedProperty.PORT_JAVA_HTTP) > 0 { key.PORT_JAVA_HTTP = updatedProperty.PORT_JAVA_HTTP; } else { key.PORT_JAVA_HTTP = key.PORT_JAVA_HTTP; }
        properties = append(properties[:i], key);
        json.NewEncoder(w).Encode(key);
    }
}
//----------
func main() {
    router := mux.NewRouter().StrictSlash(true);
    router.HandleFunc("/", getProperties).Methods("GET");
    router.HandleFunc("/", updateProperties).Methods("PATCH");
    log.Fatal(http.ListenAndServe(":4000", router));
}