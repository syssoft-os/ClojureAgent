(ns clj-agent.core
  (:gen-class))

(defn -main
  []
  ;; Create an agent with initial state 0
  (def my-agent (agent 0))

  ;; Send several functions to the agent
  (future (send my-agent inc))
  (future (Thread/sleep 1000) (send my-agent #(* % 2)))
  (future (send my-agent #(inc (* % 2))) (Thread/sleep 1000))

  ;; Wait for the agent to finish processing
  (await my-agent)

  ;; Print the state of the agent
  (println "State of my-agent: " @my-agent)
  (shutdown-agents))
