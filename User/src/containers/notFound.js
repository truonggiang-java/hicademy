import React from 'react';

export default function NotFound() {
    return (
        <React.Fragment>
            <div style={{display:'block'}}>
                <div style={
                    {
                        display: 'flex',
                        justifyContent: 'center',
                        flexDirection: 'column',
                        textAlign: 'center',
                        minHeight: 'calc(100vh - 100px)'
                    }
                }>
                    <h1
                        style={{
                            color: 'red',
                            fontSize: '100px'
                        }}
                    >TRANG NÀY KHÔNG TỒN TẠI</h1> <br />
                    <h3>Vui lòng quay trở lại trang chủ</h3>
                </div>
            </div>
        </React.Fragment>
    )
}