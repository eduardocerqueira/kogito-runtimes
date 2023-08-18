class Template {
    Object f = new org.jbpm.workflow.core.node.RuleUnitFactory<$Type$>() {
        public $Type$ bind(org.kie.api.runtime.process.ProcessContext kcontext) {
            return null;
        }
        public org.drools.ruleunits.api.RuleUnit<$Type$> unit() {
            return null;
        }
        public void unbind(org.kie.api.runtime.process.ProcessContext kcontext, $Type$ unit) {

        }
    };
}
