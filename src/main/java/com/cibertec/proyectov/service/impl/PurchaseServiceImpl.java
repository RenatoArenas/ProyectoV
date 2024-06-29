package com.cibertec.proyectov.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspose.words.Cell;
import com.aspose.words.Document;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.aspose.words.PdfSaveOptions;
import com.aspose.words.Row;
import com.aspose.words.Run;
import com.aspose.words.Table;
import com.cibertec.proyectov.model.PurchaseDetailModel;
import com.cibertec.proyectov.model.PurchaseModel;
import com.cibertec.proyectov.repository.PurchaseRepository;
import com.cibertec.proyectov.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Override
	public List<PurchaseModel> findAll() {
		return purchaseRepository.findAll();
	}

	@Override
	public PurchaseModel save(PurchaseModel purchase) {

		return purchaseRepository.save(purchase);
	}

	@Override
	public void delete(Long id) {
		
		Optional<PurchaseModel> purchaseex = purchaseRepository.findById(id);
		
		if (!purchaseex.isEmpty()) {
			PurchaseModel purchase = purchaseex.get();
			
			purchaseRepository.delete(purchase);
	    } else {
	    	throw new RuntimeException("No existe esta venta");
	    }
		
	}

	@Override
	public String report(Long id) {
		
		Optional<PurchaseModel> purchaseex = purchaseRepository.findById(id);
		PurchaseModel purchase = new PurchaseModel();
		if (!purchaseex.isEmpty()) {
			purchase = purchaseex.get();
			
	    } else {
	    	throw new RuntimeException("No existe esta venta");
	    }
		
		String filepath = "/templatedoc/repventadoc.docx";
		String filepathsave = "/templatedoc/resultrepventa.pdf";
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(".").getFile() + filepath);
		File filesave = new File(classLoader.getResource(".").getFile() + filepathsave);
		
		String base64str = "";
		
		
		
		try {
            InputStream in = new FileInputStream(file);
            
            Document doc = new Document(in);
            
            Table table = (Table) doc.getChild(NodeType.TABLE, 1, true);
            
            for(PurchaseDetailModel pdetail : purchase.getPurchasedetail()) {

                Row row = new Row(doc);
                row.getRowFormat().setAllowBreakAcrossPages(true);
                table.appendChild(row);
                
                Cell cell = new Cell(doc);	
                cell.appendChild(new Paragraph(doc));
                cell.getFirstParagraph().appendChild(new Run(doc, pdetail.getName()));

                row.appendChild(cell);
                
                cell = new Cell(doc);	
                cell.appendChild(new Paragraph(doc));
                cell.getFirstParagraph().appendChild(new Run(doc, Double.toString(pdetail.getPrice())));

                row.appendChild(cell);
                
                cell = new Cell(doc);	
                cell.appendChild(new Paragraph(doc));
                cell.getFirstParagraph().appendChild(new Run(doc, Integer.toString(pdetail.getQuantity())));

                row.appendChild(cell);
                
                cell = new Cell(doc);	
                cell.appendChild(new Paragraph(doc));
                cell.getFirstParagraph().appendChild(new Run(doc, Double.toString(pdetail.getTotal())));

                row.appendChild(cell);
            }
            
            
            OutputStream out = new FileOutputStream(filesave);
            PdfSaveOptions saveOptions = new PdfSaveOptions();
            
            doc.getRange().replace("{doc}", purchase.getDoc());
            doc.getRange().replace("{lname}", purchase.getLname());
            doc.getRange().replace("{name}", purchase.getName());
            doc.getRange().replace("{email}", purchase.getEmail());
            doc.getRange().replace("{phone}", purchase.getPhone());
            
            
            doc.save(out, saveOptions);

    		byte[] inFileBytes = Files.readAllBytes(filesave.toPath()); 
    		
    		base64str = Base64.getEncoder().encodeToString(inFileBytes);
    		
        } catch (IOException e) {
        } catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return base64str;
	}

}
