package com.abirid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.abirid.jb.Orden;

public class Etl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String inFile = "C:/informacion/OneDrive - Instituto Politecnico "
				+ "Nacional/master/inteligencia de negocios/proyecto/casoactivo/casoactivo.csv";
		String outFile = "C:/informacion/OneDrive - Instituto Politecnico "
				+ "Nacional/master/inteligencia de negocios/proyecto/casoactivo/casoactivo_out.csv";
		if(args!=null && args.length>1){
			inFile = args[0];
			outFile = args[1];
		}
		Etl etl = new Etl();
		etl.etl(inFile, outFile);

	}

	public void etl(String inFile, String outFile) {

		FileWriter outF = null;
		PrintWriter pw = null;
		Map<String, Orden> hm = new HashMap<String, Orden>();
		try {
			System.out.println("Iniciando proceso");
			System.out.println("Leyendo archivo \n" + inFile);
			// Id AñoMes Orden Banco
			FileReader fr = new FileReader(inFile);
			BufferedReader br = new BufferedReader(fr);

			outF = new FileWriter(outFile);
			pw = new PrintWriter(outF);

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] splitLine = linea.split(",");
				Orden orden = new Orden(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
				if (hm.get(orden.getCve()) == null) {
					hm.put(orden.getCve(), orden);
				} else {
					Orden ordenGuardada = hm.get(orden.getCve());
					if (ordenGuardada.getBanco() == orden.getBanco() && ordenGuardada.getOrden() == orden.getOrden()
							&& ordenGuardada.getAnio() == orden.getAnio() && ordenGuardada.getMes() < orden.getMes()) {
						hm.put(orden.getCve(), orden);
					}
				}
			}

			fr.close();
			br.close();
			fr = new FileReader(inFile);
			br = new BufferedReader(fr);
			System.out.println("Creando archivo de salida \n" + outFile);
			while ((linea = br.readLine()) != null) {
				String[] splitLine = linea.split(",");
				Orden orden = new Orden(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
				
				if (hm.get(orden.getCve()).getId().equals(orden.getId())) {

					pw.println(splitLine[0] + "," + splitLine[1] + "," + splitLine[2] + "," + "AC");
				} else {
					pw.println(splitLine[0] + "," + splitLine[1] + "," + splitLine[2] + "," + "IN");
				}
			}
			fr.close();
			br.close();
			System.out.println("Proceso terminado con exito");
		} catch (Exception e) {
			System.out.println("Error favor de revisar");
			e.printStackTrace();
		} finally {
			try {

				if (null != outF)
					outF.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

}
