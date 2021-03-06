package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.impl.ControlarDespesas;
import br.cefetmg.inf.hosten.server.ServerUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ControlarDespesasAdapter implements Runnable {

    private DatagramSocket socket;
    private DatagramPacket [] pacotesRecebidos;

    private final ArrayList listaRecebida;
    private Object objEnviado;
    private String tipoRetorno;

    public ControlarDespesasAdapter(
            DatagramSocket socket, DatagramPacket [] pacotesRecebidos) 
            throws IOException, ClassNotFoundException {
        this.socket = socket;
        this.pacotesRecebidos = pacotesRecebidos;
        
        ByteArrayOutputStream matrizArray = new ByteArrayOutputStream();
        for (DatagramPacket pacotesRecebidos1 : pacotesRecebidos) {
            matrizArray.write(pacotesRecebidos1.getData());
        }
        byte[] vetorArray = matrizArray.toByteArray();

        listaRecebida = (ArrayList) ServerUtils.toObject(vetorArray);
    }

    private void operacao() {
//        System.err.println("Iniciando operação no adapter...");

        String operacao = (String) listaRecebida.get(1);

        try {
            IControlarDespesas controlarDespesas = new ControlarDespesas();

            switch (operacao) {
                case "Inserir": {
                    tipoRetorno = "Boolean";
                    QuartoConsumo itemInserir = (QuartoConsumo) listaRecebida.get(2);
                    objEnviado = controlarDespesas.inserir(itemInserir);
                    break;
                }
                case "Listar": {
                    tipoRetorno = "List<Despesa>";
                    int seqHospedagem = (int) listaRecebida.get(2);
                    int nroQuarto = (int) listaRecebida.get(3);
                    objEnviado = controlarDespesas.listar(seqHospedagem, nroQuarto);
                    
                    break;
                }
                case "Excluir": {
                    tipoRetorno = "Boolean";
                    QuartoConsumo qrtConsumo = (QuartoConsumo) listaRecebida.get(2);
                    objEnviado = controlarDespesas.excluir(qrtConsumo);
                    
                    break;
                }
                case "RetornarRelatorioDespesas": {
                    tipoRetorno = "Map<String, Object>";
                    int seqHospedagem = (int) listaRecebida.get(2);
                    int nroQuarto = (int) listaRecebida.get(3);
                    objEnviado 
                            = controlarDespesas
                                    .retornaRelatorioDespesas(
                                            seqHospedagem, nroQuarto);
                    
                    break;
                }
            }
        } catch (Exception ex) {
            tipoRetorno = "Exception";
            objEnviado = ex;
        }
    }

    @Override
    public void run() {
        operacao();

        try {
            ArrayList listaEnviada = new ArrayList();
            listaEnviada.add(tipoRetorno);
            listaEnviada.add(objEnviado);

//            byte[] out = new byte[ServerUtils.TAMANHO];
//            out = ServerUtils.toByteArray(listaEnviada);
//
//            System.out.println("Pacote de retorno montado!");
//
//            InetAddress IPAddress = pacotesRecebidos.getAddress();
//            int port = pacotesRecebidos.getPort();
//            DatagramPacket sendPacket = new DatagramPacket(out, out.length, IPAddress, port);
//
//            System.out.println("pacote de retorno enviado!!");
//            socket.send(sendPacket);
//            System.out.println("Pacotes de retorno sendo montados!");
            
            byte [][] out = ServerUtils.toByteArray(listaEnviada);

            InetAddress IPAddress = pacotesRecebidos[0].getAddress();
            int port = pacotesRecebidos[0].getPort();

//            System.out.println("Serão enviados " + out[0][0] + " pacotes de retorno");
            DatagramPacket pacoteNumPacotes = new DatagramPacket(out[0], out[0].length, IPAddress, port);
            socket.send(pacoteNumPacotes);
            for (int i = 1; i <= out[0][0]; i++) {
//                System.out.println("enviando pacote " + i);
                DatagramPacket DpSend = new DatagramPacket(out[i], out[i].length, IPAddress, port);
                socket.send(DpSend);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}