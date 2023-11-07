package br.com.api.g4.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {

	public static boolean fazerUploadImagem(MultipartFile imagem) {
	
		boolean sucessoUpload = false;
		
		if(!imagem.isEmpty()) {
			String nomeArquivo = imagem.getOriginalFilename();
			try {
				
				//criando diretorio para armazenar o arquivo
				String pastaUploadImagem = "C:\\Users\\rgall\\git\\trabalho-em-grupo-g4\\grupo4\\src\\main\\resources\\img";
				File dir = new File(pastaUploadImagem);
				if(!dir.exists()) {
					dir.mkdirs();
				}
				
				File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo );
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				
				stream.write(imagem.getBytes());
				stream.close();
				System.out.println("Armazenado em:" + serverFile.getAbsolutePath());
				System.out.println("Você fez o upload do arquivo" + nomeArquivo + "com sucesso.");
				
				
			}catch(Exception e) {
				
				System.out.println("Falha no upload do arquivo:" + nomeArquivo + "=>"+ e.getMessage());
				
			}
		}else {
			System.out.println("Você falhou em carregar o arquivo por que ele está vazio!");
			
		}
		return sucessoUpload;
	}
}
