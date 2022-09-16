from pdf2docx import Converter

pdf_file = './sample.PDF'
docx_file = './sample.docx'

# convert pdf to docx
cv = Converter(pdf_file)
cv.convert(docx_file) # 默认参数start=0, end=None
cv.close()