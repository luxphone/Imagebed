package ee.ifl.imagebedapi.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "tb_imagebed")
public class Imagebed implements Serializable {
    @Id(keyType = KeyType.Auto)
    private Integer id;
    private String src;
    private Date createtime;
}
