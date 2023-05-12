import { goldenContainerStyle } from '../../utils/styles'
import { useNavigate } from 'react-router-dom'

const Box = (value) => {
    return <div>{value}</div>
}

const ClickList = ({ active, list, item, path, id, styles, customStyles }) => {
    const navigate = useNavigate()
    return list.map((value, index) => {
        let style = styles ? (Array.isArray(styles) ? styles[index] : styles) : goldenContainerStyle
        style = JSON.parse(JSON.stringify(style));
        style.cursor = 'pointer'

        if (customStyles) {
            const customItemStyles = customStyles(value);
            style = { ...style, ...customItemStyles };
          }

        return (
            <div key={index} style={style} onClick={(e) => {
                e.stopPropagation()
                if (path) {
                    let key = id ? value[id] : value
                    if (active !== index) navigate(path + '/' + key)
                    else navigate(path)
                }
            }}>
                {item ? item(value, active === index) : Box(value)}
            </div>
        )
    })
}

export default ClickList