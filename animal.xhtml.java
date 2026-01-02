<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:f="http://xmlns.jcp.org/jsf/core"
      xmlns:p="http://primefaces.org/ui">

<h:head>
    <title>Gerenciamento de Animais</title>
</h:head>

<h:body>
    <div style="max-width: 900px; margin: 0 auto; padding: 20px;">
        <h1 style="text-align: center; color: #2E8B57;">Gerenciamento de Animais - Vacinas</h1>

        <h:form>
            <p:panelGrid columns="2" style="margin-bottom: 30px;">

                <p:outputLabel value="Nome:" for="nome"/>
                <p:inputText id="nome" value="#{animalBean.animal.nome}" required="true"/>

                <p:outputLabel value="Dono:" for="dono"/>
                <p:inputText id="dono" value="#{animalBean.animal.dono}" required="true"/>

                <p:outputLabel value="Telefone:" for="telefone"/>
                <p:inputText id="telefone" value="#{animalBean.animal.telefone}" required="true"/>

                <p:outputLabel value="Tipo:" for="tipo"/>
                <p:selectOneMenu id="tipo" value="#{animalBean.animal.tipo}" converter="javax.faces.Character" required="true">
                    <f:selectItem itemLabel="Selecione o tipo" itemValue="#{null}"/>
                    <f:selectItem itemLabel="Cachorro" itemValue="C"/>
                    <f:selectItem itemLabel="Gato" itemValue="G"/>
                </p:selectOneMenu>

                <p:outputLabel value="Data de Nascimento:" for="nascimento"/>
                <p:calendar id="nascimento" value="#{animalBean.animal.nascimento}" pattern="dd/MM/yyyy"/>

                <f:facet name="footer">
                    <p:commandButton value="Salvar"
                                     action="#{animalBean.salvar}"
                                     update="@form formTabela"
                                     style="margin-top: 20px;"/>
                </f:facet>
            </p:panelGrid>

            <p:messages autoUpdate="true" closable="true"/>
        </h:form>

        <h:form id="formTabela">
            <p:dataTable value="#{animalBean.animais}" var="a" style="margin-top: 40px;">

                <p:column headerText="ID">
                    <h:outputText value="#{a.id}"/>
                </p:column>

                <p:column headerText="Nome">
                    <h:outputText value="#{a.nome}"/>
                </p:column>

                <p:column headerText="Dono">
                    <h:outputText value="#{a.dono}"/>
                </p:column>

                <p:column headerText="Telefone">
                    <h:outputText value="#{a.telefone}"/>
                </p:column>

                <p:column headerText="Tipo">
                    <h:outputText value="#{animalBean.getTipoFormatado(a)}"/>
                </p:column>

                <p:column headerText="Ações">
                    <p:commandButton value="Excluir" action="#{animalBean.excluir}" update="@form">
                        <f:setPropertyActionListener value="#{a}" target="#{animalBean.animal}"/>
                    </p:commandButton>
                </p:column>

            </p:dataTable>
        </h:form>
    </div>
</h:body>
</html>
