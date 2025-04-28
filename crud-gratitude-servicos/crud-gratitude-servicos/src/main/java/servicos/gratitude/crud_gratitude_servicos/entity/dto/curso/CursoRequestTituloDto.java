package servicos.gratitude.crud_gratitude_servicos.entity.dto.curso;

public class CursoRequestTituloDto {

    private  String tituloCurso;


    public CursoRequestTituloDto(String tituloCurso) {
        this.tituloCurso = tituloCurso;
    }

    public CursoRequestTituloDto() {
    }

    public String getTituloCurso() {
        return tituloCurso;
    }

    public void setTituloCurso(String tituloCurso) {
        this.tituloCurso = tituloCurso;
    }
}
