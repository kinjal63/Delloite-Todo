var path = require('path');

module.exports = {
	entry : {
		app: ['./src/main/resources/public/js/components/app.js']
	},
	devtool : 'sourcemaps',
	cache : true,
	mode : 'development',
	output : {
//		path : __dirname + '/src/main/resources/public/dist',
		path : __dirname + '/target/classes/public/dist',
		filename : 'bundle.js'
	},
	module : {	
		rules : [{
					test : path.join(__dirname, '.'),
					exclude : /(node_modules)/,
					use : [ {
						loader : 'babel-loader',
						options : {
							presets : [ "@babel/preset-env", "@babel/preset-react" ]
						}
					} ]
				},
				{
			        test: /\.css$/,
			        use: ['style-loader', 'css-loader']
				}
		]
	}
}
